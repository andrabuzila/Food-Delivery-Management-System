package presentationLayer;

public class Register {
    private String username;
    private String pass;

    Register(String u, String p){
        this.username=u;
        this.pass=p;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
