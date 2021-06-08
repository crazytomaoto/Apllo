package com.sz.apollo.ui.models;

public class TokenKindBean {
    private String name;
    private String contractAddress;
    private boolean isSelected;
    private boolean canBeSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isCanBeSet() {
        return canBeSet;
    }

    public void setCanBeSet(boolean canBeSet) {
        this.canBeSet = canBeSet;
    }
}
