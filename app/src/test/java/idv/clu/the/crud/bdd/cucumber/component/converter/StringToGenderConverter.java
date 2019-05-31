package idv.clu.the.crud.bdd.cucumber.component.converter;

import cucumber.deps.com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import idv.clu.the.crud.module.user.model.Gender;

/**
 * @author Carl Lu
 */
public class StringToGenderConverter extends AbstractSingleValueConverter {

    @Override
    public boolean canConvert(Class type) {
        return type.equals(Gender.class);
    }

    @Override
    public Object fromString(String genderStr) {
        return genderStr.isEmpty() ? null : Gender.valueOf(genderStr);
    }

}
