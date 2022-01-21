package com.apitest.UIAutomation.pages.wrappers;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private String page;
    private List<Element> elements = new ArrayList();

    public String getPage() {
        return this.page;
    }

    public List<Element> getElements() {
        return this.elements;
    }

    public void setPage(final List<Element> elements) {
        this.elements = elements;
    }

    public void setElements(final List<Element> elements) {
        this.elements = elements;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Page)) {
            return false;
        } else {
            Page other = (Page) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$page = this.getPage();
                Object other$page = other.getPage();
                if (this$page != null) {
                    if (other$page != null) {
                        return false;
                    }
                } else if (!this$page.equals(other$page)) {
                    return false;
                }

                Object this$elements = this.getElements();
                Object other$elements = other.getElements();
                if(this$elements == null){
                    if(other$elements != null){
                        return false;
                    }
                } else if(!this$elements.equals(other$elements)){
                    return false;
                }
                return true;
            }
        }
    }

    protected boolean canEqual(final Object other){
        return other instanceof Page;
    }

}
