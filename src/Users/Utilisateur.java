/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;
import java.util.Date;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ASUS
 */
public class Utilisateur {
    
    private int id;
    private String nom;
    private String prenom;
    private int cin;
    private Date dn;
    private String role;
    private int tel;
    private int longtitude_user;
    private int altitude_user;
    private String email;
    private String password;
    private String disponibilite;

    public Utilisateur() {
    }
    
      String url = "jdbc:mysql://localhost:3306/debbov1";
     String username = "root";
     String pword = "";
     Connection con;
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getLongtitude_user() {
        return longtitude_user;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    

    public void setLongtitude_user(int longtitude_user) {
        this.longtitude_user = longtitude_user;
    }

    public int getAltitude_user() {
        return altitude_user;
    }

    public void setAltitude_user(int altitude_user) {
        this.altitude_user = altitude_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public Date getDn() {
        return dn;
    }

    public void setDn(Date dn) {
        this.dn = dn;
    }
  @Override  
  public String toString(){
      return "Id :"+this.getId()+"\n"+"Nom :"+this.getNom()+"\n"+"Prénom :"+this.getPrenom()+"\n"+"Cin :"+this.getCin()+"\n"+
              "Date de naissance :"+this.getDn()+"\n"+"Role :"+this.getRole()+"\n"+"Coordonneés(longtitude,altitude) : ("+this.getLongtitude_user()+","+this.getAltitude_user()+")"+"\n"+
              "Email :"+this.getEmail()+"\n"+"Disponibilité :"+this.getDisponibilite();
              
  }
  
    public ResultSet afficherDonneesUser() throws SQLException{

      con = (Connection) DriverManager.getConnection(url, username, pword);
      String req="SELECT * FROM `utilisateur` WHERE `utilisateur`.`id_user` = "+this.getId();
      Statement st=con.createStatement();
      ResultSet rst = st.executeQuery(req);
    return rst;
}
    
     public void supprimerUser() throws SQLException{

      con = (Connection) DriverManager.getConnection(url, username, pword);
      String req="DELETE FROM `utilisateur` WHERE `utilisateur`.`id_user` = "+this.getId();
         System.out.println(req);
      Statement st=con.createStatement();
      st.executeUpdate(req);
      
      
     }
  
     public void ajouterAuBd() throws SQLException {
         con = (Connection) DriverManager.getConnection(url, username, pword);
         String req="INSERT INTO `utilisateur` (`id_user`, `nom`, `prenom`, `cin`, `date`, `role`, `tel`, `longitude_user`, `altitude_user`, `email`, `password`, `disponniblite`, `nbr_maxComm`, `FK_id_produit`) VALUES "
                 + "(NULL, '"+this.getNom()+" '"+this.getPrenom()+"', '"+this.getCin()+"', '"+this.getDn()+"', '"+this.getRole()+"', '"+this.getTel()+"', '"+this.getLongtitude_user()+"', '"+this.getAltitude_user()+"', '"+this.getEmail()+"', '"+this.getPassword()+"', '"+this.getDisponibilite()+"', NULL, '')";
         Statement st=con.createStatement();
            st.executeUpdate(req);
         
     } 
     
       public void changerMdp(String amdp,String nmdp) throws SQLException{
           con = (Connection) DriverManager.getConnection(url, username, pword);
       if (this.getPassword().equals("amdp")){
           this.setPassword(nmdp);
           String req="UPDATE `utilisateur` SET `password`="+nmdp+" WHERE `utilisateur`.`id_user` = "+this.getId();
           
           
           System.out.println("Password changed succesfully !");
       }
       else System.out.println("old password is uncorrect , please try again !");
    }
     
     
     
     
     
     
     
     
   
  
}
