package sample.java.xyz.srclab.spring.boot.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import xyz.srclab.common.codec.aes.AesKeys;
import xyz.srclab.spring.boot.lang.EncodeString;

import javax.crypto.SecretKey;

@SpringBootTest(classes = Starter.class)
//@ContextConfiguration(classes = {TestStarter.class})
public class LangSample extends AbstractTestNGSpringContextTests {

    private static final Logger logger = LoggerFactory.getLogger(LangSample.class);

    @Value("AES,BASE64:rliqBhMdiKQDcH8lqNZdIg==")
    private EncodeString encodeString;

    @Test
    public void testEncodeString() {
        logger.info("encodeString: {}", encodeString);
        SecretKey key = AesKeys.newKey("123");
        Assert.assertEquals(encodeString.decodeString(key), "some password");
    }
}