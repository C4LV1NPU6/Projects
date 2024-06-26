package unittests;

import daos.AuthTokenDao;
import daos.DataAccessException;
import daos.Database;
import models.AuthToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDAOTest {
    private Database db;
    private AuthToken bestToken;
    private AuthTokenDao eDao;

    @BeforeEach
    public void setUp() throws DataAccessException
    {
        //here we can set up any classes or variables we will need for the rest of our tests
        //lets create a new database
        db = new Database();
        //and a new authtoken with random data
        bestToken = new AuthToken("118000", "human_118");
        //Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        //Let's clear the database as well so any lingering data doesn't affect our tests
        db.clearTables();
        //Then we pass that connection to the AuthTokenDAO so it can access the database
        eDao = new AuthTokenDao(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        //Here we close the connection to the database file so it can be opened elsewhere.
        //We will leave commit to false because we have no need to save the changes to the database
        //between test cases
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        //While insert returns a bool we can't use that to verify that our function actually worked
        //only that it ran without causing an error
        eDao.insert(bestToken);
        //So lets use a find method to get the authtoken that we just put in back out
        AuthToken compareTest = eDao.find(bestToken.getAuthtoken());
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNotNull(compareTest);
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our insert did put something in, and that it didn't change the
        //data in any way
        assertEquals(bestToken, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        //lets do this test again but this time lets try to make it fail
        //if we call the method the first time it will insert it successfully
        eDao.insert(bestToken);
        //but our sql table is set up so that "authtoken" must be unique. So trying to insert it
        //again will cause the method to throw an exception
        //Note: This call uses a lambda function. What a lambda function is is beyond the scope
        //of this class. All you need to know is that this line of code runs the code that
        //comes after the "()->" and expects it to throw an instance of the class in the first parameter.
        assertThrows(DataAccessException.class, ()-> eDao.insert(bestToken));
    }

    @Test
    public void findPass() throws DataAccessException {
        //While insert returns a bool we can't use that to verify that our function actually worked
        //only that it ran without causing an error
        eDao.insert(bestToken);
        //So lets use a find method to get the authtoken that we just put in back out
        AuthToken compareTest = eDao.find(bestToken.getAuthtoken());
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNotNull(compareTest);
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our find found the right authtoken
        assertEquals(bestToken, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        //lets do this test again but this time lets try to make it fail
        //if we clear the database then there will be no authtokens to find
        eDao.clear();
        //searching for an authtoken will cause the method to return null
        AuthToken compareTest = eDao.find(bestToken.getAuthtoken());
        assertNull(compareTest);
    }

    @Test
    public void clearPass() throws DataAccessException {
        eDao.insert(bestToken);

        AuthToken compareTest1 = eDao.find(bestToken.getAuthtoken());
        assertNotNull(compareTest1);

        eDao.clear();

        AuthToken compareTest2 = eDao.find(bestToken.getAuthtoken());
        assertNull(compareTest2);
    }
}
