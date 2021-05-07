package tn.fynova.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="communication")
public class Communication implements Serializable {

    private static final long serialVersionUID = 1L;
     
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int communication_id;
    
	@ManyToOne()
    @JoinColumn(name = "id_sender")
    private User sender;

    @ManyToOne()
    @JoinColumn(name = "id_receiver")
    private User receiver;

    @Column(name="message")
    private String message;
    
    @Temporal(TemporalType.DATE)
	private Date date_msg;
}
