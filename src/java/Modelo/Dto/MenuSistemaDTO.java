
package Modelo.Dto;


public class MenuSistemaDTO {
    private Integer id;
    private String  descrip;
    private String  comentario;
    private String  token;
    
    public MenuSistemaDTO() {
        //des
    }

    public MenuSistemaDTO(Integer id) {
        this.id = id;
    }
    
    public MenuSistemaDTO(Integer id, String descrip) {
        this.id = id;
        this.descrip = descrip;
    }
        
    
    public void setId(Integer id ){
        this.id = id;
    }
    
    public void setDescrip( String  descrip ){
        this.descrip = descrip;
    }
    
    public void setComentario( String  comentario){
        this.comentario = comentario;
    }
    
    public Integer getId(){
        return id;
    }
    
    public String getDescrip(){
        return descrip;
    }
    
    public String getComentario(){
        return comentario;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    
    

}
