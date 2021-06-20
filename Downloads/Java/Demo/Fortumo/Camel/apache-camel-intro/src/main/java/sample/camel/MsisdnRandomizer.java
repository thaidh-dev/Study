package sample.camel;

import io.github.benas.randombeans.api.Randomizer;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by NghiaTM on 7/29/2016.
 */
public class MsisdnRandomizer implements Randomizer<String> {

    public String getRandomValue() {
        return "849" + RandomStringUtils.random(9);
    }
}
