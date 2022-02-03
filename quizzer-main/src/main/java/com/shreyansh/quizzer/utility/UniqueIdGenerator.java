package com.shreyansh.quizzer.utility;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;

/**
 * @Author Shreyansh Jain
 * Class UniqueIdGenerator is responsible to create a 42 Character Length Alphanumeric Unique Identity.
 * It will be the Primary Key for all the tables defined in this microservices.
 * **/

public class UniqueIdGenerator implements IdentifierGenerator {
    public static final String generatorName = "myGenerator";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        final int sizeOfCharacters = 24;
        String alphaNumericString =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(sizeOfCharacters);

        for (int i = 0; i < sizeOfCharacters; i++) {
            // generate a random number between
            // 0 to alphaNumericString variable length
            int index = (int) (alphaNumericString.length() * Math.random());
            // add Character one by one in end of sb
            sb.append(alphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
