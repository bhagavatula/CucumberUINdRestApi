package com.apitest.UIAutomation.pages.wrappers;

import java.util.Map;

public class Element {
    private String name;
    private String type;
    private boolean screenShot;
    private Map<String, String> locators;

    public Element() {

    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public boolean isScreenShot() {
        return this.screenShot;
    }

    public Map<String, String> getLocators() {
        return this.locators;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public void setScreenShot(final boolean screenShot) {
        this.screenShot = screenShot;
    }

    public void setLocators(final Map<String, String> locators) {
        this.locators = locators;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Element)) {
            return false;
        } else {
            Element other = (Element) o;
            if (!other.equals(this)) {
                return false;
            } else {
                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name == null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$type = this.getType();
                Object other$type = other.getType();
                if (this$type == null) {
                    if (other$type != null) {
                        return false;
                    }
                } else if (!this$type.equals(other$type)) {
                    return false;
                }
                if (this.isScreenShot() != other.isScreenShot()) {
                    return false;
                } else {
                    Object this$locators = this.getLocators();
                    Object other$locators = other.getLocators();
                    if (this$locators == null) {
                        if (other$locators == null) {
                            return false;
                        }
                    } else if (!this$locators.equals(other$locators)) {
                        return false;
                    }
                    return true;
                }
            }
        }

    }

}
