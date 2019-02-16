package assignment.progresssoft.jobfinder.models;

public class ProviderItem {

    private String providerName;
    private int providerImage;

    public ProviderItem(String providerName, int providerImage) {
        this.providerName = providerName;
        this.providerImage = providerImage;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public int getProviderImage() {
        return providerImage;
    }

    public void setProviderImage(int providerImage) {
        this.providerImage = providerImage;
    }
}
