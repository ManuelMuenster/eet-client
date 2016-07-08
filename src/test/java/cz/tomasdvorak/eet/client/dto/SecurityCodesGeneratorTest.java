package cz.tomasdvorak.eet.client.dto;

import cz.etrzby.xml.TrzbaDataType;
import cz.tomasdvorak.eet.client.security.ClientKey;
import cz.tomasdvorak.eet.client.utils.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import java.math.BigDecimal;

public class SecurityCodesGeneratorTest {

    private SecurityCodesGenerator securityCodesGenerator;

    @Before
    public void setUp() throws Exception {
        final ClientKey clientKey = new ClientKey(getClass().getResourceAsStream("/keys/01000005.p12"), "eet");
        securityCodesGenerator = new SecurityCodesGenerator(clientKey);
    }

    @Test
    public void toBKP() throws Exception {
        Assert.assertEquals("72897FE0-69A48F22-5020319C-369F7A7E-987807B8", securityCodesGenerator.getBKP(getData()));
    }

    @Test
    public void toPlaintext() throws Exception {
        final TrzbaDataType data = getData();
        Assert.assertEquals("CZ72080043|243|24/A-6/Brno_2|#135433c/11/2016|2016-12-09T16:45:36+01:00|3264.00", SecurityCodesGenerator.serializeData(data));
    }

    private TrzbaDataType getData() throws DatatypeConfigurationException {
        final TrzbaDataType data = new TrzbaDataType();
        data.setDicPopl("CZ72080043");
        data.setIdProvoz(243);
        data.setIdPokl("24/A-6/Brno_2");
        data.setPoradCis("#135433c/11/2016");
        data.setDatTrzby(DateUtils.parse("2016-12-09T16:45:36+01:00"));
        data.setCelkTrzba(new BigDecimal("3264.00"));
        return data;
    }
}