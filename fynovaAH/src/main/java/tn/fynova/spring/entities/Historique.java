package tn.fynova.spring.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Historique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Status ;

    @ManyToOne
    private Account account ;

    @ManyToOne
    private  User user ;

    private Date dateh ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateh() {
        return dateh;
    }

    public void setDateh(Date dateh) {
        this.dateh = dateh;
    }

    public Historique() {
    }

}
