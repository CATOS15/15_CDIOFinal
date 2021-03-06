package Security;

import Model.DAO.IUserDAO;
import Model.DAO.UserDAO;
import Model.DTO.Rolle;
import Model.DTO.User;
import Model.Exception.DALException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;

public class Security {
    public static String crypt(String password){
        try {
            password = "missetand" + password;
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashtext = new StringBuilder(no.toString(16));
            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }
            return hashtext.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static String generateToken(String username) throws DALException {
        try {
            Date dateOneDay = new Date(System.currentTimeMillis() + 3600 * 1000 * 24);

            Algorithm algorithm = Algorithm.HMAC256("privatekey_n!sseMand3_5");
            return JWT.create()
                    .withIssuer("auth0")
                    .withExpiresAt(dateOneDay)
                    .withClaim("user", username)
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new DALException("Kunne ikke oprette token");
        }
    }
    public static String verifyToken(String token, RolleEnum rolleNeeded) throws DALException {
        try {
            Algorithm algorithm = Algorithm.HMAC256("privatekey_n!sseMand3_5");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Claim claim = jwt.getClaim("user");

            if(rolleNeeded != null && rolleNeeded != RolleEnum.NULL){
                IUserDAO iUserDAO = new UserDAO();
                boolean accessGranted = false;
                User user = iUserDAO.getUserByName(claim.asString());
                for (Rolle rolle : user.getRoller()) {
                    switch (rolleNeeded){
                        case ADMINISTRATOR:
                            if(rolle.getRoleId() == 1){
                                accessGranted = true;
                            }
                        break;
                        case FARMACEUT:
                            if(rolle.getRoleId() == 2 || rolle.getRoleId() == 1){
                                accessGranted = true;
                            }
                        break;
                        case PRODUKTIONSLEDER:
                            if(rolle.getRoleId() == 3 || rolle.getRoleId() == 2 || rolle.getRoleId() == 1){
                                accessGranted = true;
                            }
                        break;
                        case LABORANT:
                            if(rolle.getRoleId() == 4 || rolle.getRoleId() == 3 || rolle.getRoleId() == 2 || rolle.getRoleId() == 1){
                                accessGranted = true;
                            }
                        break;
                    }
                }
                if(!accessGranted) throw new DALException("Din rolle giver der ikke adgang til denne side");
            }
            return claim.asString();
        } catch (JWTVerificationException | ClassNotFoundException | SQLException exception){
            throw new DALException("Invalid token");
        }
    }
}
