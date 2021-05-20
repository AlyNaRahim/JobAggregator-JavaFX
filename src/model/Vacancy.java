package model;

public class Vacancy {
    private String title;
    private String salary;
    private String country;
    private String companyName;
    private String siteName;
    private String url;

    public Vacancy(String title, String companyName, String country, String salary) {
        this.title = title;
        this.salary = salary;
        this.country = country;
        this.companyName = companyName;
    }
    public Vacancy (){

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setCountry(String city) {
        this.country = city;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

//    public void setSiteName(String siteName) {
//        this.siteName = siteName;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }

    public String getTitle() {
        return title;
    }

    public String getSalary() {
        return salary;
    }

    public String getCountry() {
        return country;
    }

    public String getCompanyName() {
        return companyName;
    }

//    public String getSiteName() {
//        return siteName;
//    }
//
//    public String getUrl() {
//        return url;
//    }


    @Override
    public String toString() {
        return getTitle() +"\n"+
                getCompanyName() + "\n" + getSalary();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Vacancy)) return false;

        Vacancy vacancy = (Vacancy) o;

        if (!title.equals(vacancy.title)) return false;
        if (!salary.equals(vacancy.salary)) return false;
        if (!country.equals(vacancy.country)) return false;
        if (!companyName.equals(vacancy.companyName)) return false;
        if (!siteName.equals(vacancy.siteName)) return false;
        return url.equals(vacancy.url);

    }

        @Override
        public int hashCode()
        {
            int result = title.hashCode();
            result = 31 * result + salary.hashCode();
            result = 31 * result + country.hashCode();
            result = 31 * result + companyName.hashCode();
            result = 31 * result + siteName.hashCode();
            result = 31 * result + url.hashCode();
            return result;
        }


}