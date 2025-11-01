
package entidades;

/**
 *
 * @author Oscar Solis Barrientos
 */
public class Usuario {
    private int id;
    private String correo;
    private String clave;
    private String salt;
    private boolean activo;
    private String username;
    private String nombreC;

    public Usuario() {
    }

    public Usuario(String correo, String clave, String salt, String username, String nombreC) {
        this.correo = correo;
        this.clave = clave;
        this.salt = salt;
        this.username = username;
        this.nombreC = nombreC;
    }

    

    

    
    
    
    
    public Usuario(int id, String correo, String clave, String salt, boolean activo, String username) {
        this.id = id;
        this.correo = correo;
        this.clave = clave;
        this.salt = salt;
        this.activo = activo;
        this.username = username;
    }
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }
    
    
}
