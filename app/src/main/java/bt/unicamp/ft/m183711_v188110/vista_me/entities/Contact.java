package bt.unicamp.ft.m183711_v188110.vista_me.entities;

import bt.unicamp.ft.m183711_v188110.vista_me.enumerations.e_TypeContact;

public class Contact {
    private String Name;
    private e_TypeContact typeContact;
    private String value;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public e_TypeContact getTypeContact() {
        return typeContact;
    }

    public void setTypeContact(e_TypeContact typeContact) {
        this.typeContact = typeContact;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
