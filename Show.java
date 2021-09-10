public class Show implements java.io.Serializable {

    public String name;
    public String type;
    public String levity;
    public String progression;
    public int episodes;

    public Show(){
        type = null;
        levity = null;
        progression = null;
    }

    public Show(String name, String type, String levity, String progression, int eps){
        this.name = name;
        this.type = type;
        this.levity = levity;
        this.progression = progression;
        episodes = eps;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setLevity(String levity){
        this.levity = levity;
    }

    public void setProgression(String progression){
        this.progression = progression;
    }

    public String toString(){
        return String.format("%s - %d episodes\n", name, episodes);
    }
}
