package ugh.dl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MetadataGroup implements Serializable {

    private static final long serialVersionUID = -2935555025180170310L;

    private List<MetadataType> metadataTypeList = new ArrayList<MetadataType>();

    // Unique name of MetadataType.
    private String name;

    // Maximum number of occurences of this MetadataType for one DocStrct (can
    // be 1 (1), one or more (+) or as many as you want (*).
    private String max_number;

    // Hash containing all languages.
    private HashMap<String, String> allLanguages;

    public List<MetadataType> getMetadataTypeList() {
        return metadataTypeList;
    }

    public void setMetadataTypeList(List<MetadataType> metadataTypeList) {
        this.metadataTypeList = metadataTypeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMetadataType(MetadataType metadataToAdd) {
        if (!metadataTypeList.contains(metadataToAdd)) {
            metadataTypeList.add(metadataToAdd);
        }
    }

    public void removeMetadataType(MetadataType metadataToRemove) {
        if (metadataTypeList.contains(metadataToRemove)) {
            metadataTypeList.remove(metadataToRemove);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((MetadataGroup) obj).getName());
    }

    public HashMap<String, String> getAllLanguages() {
        return allLanguages;
    }

    public void setAllLanguages(HashMap<String, String> allLanguages) {
        this.allLanguages = allLanguages;
    }

    /***************************************************************************
     * @param in
     * @return
     **************************************************************************/
    public boolean setNum(String in) {

        if (!in.equals("1m") && !in.equals("1o") && !in.equals("+") && !in.equals("*")) {
            // Unknown syntax.
            return false;
        }
        this.max_number = in;

        return true;
    }

    /***************************************************************************
     * <p>
     * Retrieves the number of possible Metadata objects for a DocStruct. This is now based on the type of DocStruct and is therefor stored in the
     * DocStructType.
     * </p>
     * 
     * TODO Was set to deprecated, who knows why?
     * 
     * @return number of MetadataType
     **************************************************************************/
    public String getNum() {
        return this.max_number;
    }

    public MetadataGroup copy() {

        MetadataGroup newMDType = new MetadataGroup();

        newMDType.setAllLanguages(this.allLanguages);
        newMDType.setName(this.name);
        if (this.max_number != null) {
            newMDType.setNum(this.max_number);
        }
        List<MetadataType> newList = new LinkedList<MetadataType>();
        for (MetadataType mdt : metadataTypeList) {
            newList.add(mdt.copy());
        }
        newMDType.setMetadataTypeList(newList);

        return newMDType;
    }
}