package com.example.selectcontact;

import java.io.Serializable;

public class Contact implements Serializable {
    String id;
    String  name;
    String number;
    private boolean checked;

    public Contact(String id, String name, String number,boolean checked) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.checked=checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
