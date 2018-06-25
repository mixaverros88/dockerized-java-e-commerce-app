/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entities.Custvend;
import entities.Photos;
import entities.Prodcategory;
import entities.Product;
import entities.Produnit;
import helpers.ConvertToGreeklish;
import helpers.DateTime;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import sessionsBeans.CategoryFacade;
import sessionsBeans.CustvendFacade;
import sessionsBeans.ProductFacade;
import sessionsBeans.ProductUnitFacade;
import sessionsBeans.ProdunitFacade;
import sessionsBeans.UploadImageFacade;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class ProductAddManage implements Serializable{
    
    @NotNull(message = "Παρακαλώ συμπληρώστε το όνομα του προϊόντος")  
    private String name;
    private String isactive;

    @NotNull(message = "Παρακαλώ συμπληρώστε την τιμή του προϊόντος") 
    private float buyprice;
    private String remarks;
    private Product product;
    private float sellprice;
    private Prodcategory prodcategory;
    private List<Product> products;
    private List<Prodcategory> prodcategorys;
    private List<Custvend> custvends;
    private Custvend custvend;
    private int prodcategoryid;
    private Produnit produnit;
    private List<Produnit> produnits;
    private Photos photos;
    private List<Photos> photosList;




    @EJB
    ProductFacade productFacade;
    
    @EJB
    CategoryFacade categoryFacade;

    @EJB
    ProductUnitFacade productUnitFacade;
    
    @EJB
    UploadImageFacade uploadImageFacade;
    
    @EJB
    CustvendFacade custvendFacade;
    
    @PostConstruct
    void init(){
        custvends = custvendFacade.getAllCustvendVendorFromDB();
        prodcategorys = categoryFacade.getAllCategoriesWhereIsActive();
        photosList = uploadImageFacade.getAllfiles();
        produnits = productUnitFacade.getAllProdunit();
    }
        
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    
    public String editProduct(){

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        int productId = Integer.parseInt(params.get("productID"));
        Product u =  productFacade.getProductFromDatabaseByID(productId);
        
        sessionMap.put("editProduct", u);
  
        return "/productEdit.xhtml?faces-redirect=true";
    }
        
    public List<Product> getAllUserData(){
        return products = productFacade.getAllProductsFromDatabase();
    }
    
    public String deleteProduct(int id){
        if (productFacade.deleteProductFromDatabase(id) == 1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("To προϊόν διαγράφτηκε επιτυχώς"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("To προϊόν δεν διαγράφτηκε επιτυχώς"));
        }
        return "";
    }
    
    public String changeStatusProduct(int status, int id){
        if (productFacade.changeStatusProductFromDatabase(status, id) == 1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("To προϊόν είναι ανεργό"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("To προϊόν είναι ενεργό"));
        }
        
        return "";
    }
    public String productInsert(){
        
        boolean mr=false;
        Product productInsert = new Product();
        productInsert.setName(name);
        productInsert.setSlugname(ConvertToGreeklish.greek2Roman(name));
        
        int isactiveInt;
        if(isactive.equals("true")){
            isactiveInt=1;
        }else{
            isactiveInt=0;
        }
        productInsert.setIsactive((short) isactiveInt);
        productInsert.setIsvisible((short) 1);
        productInsert.setProdcategoryid(prodcategory);
        productInsert.setProdunitid(produnit);
        productInsert.setBuyprice(buyprice);
        productInsert.setSellprice(sellprice);
        productInsert.setVendor(custvend);
        productInsert.setQty(0);
        productInsert.setPhotosid(photos); 
        productInsert.setRemarks(remarks); 
        productInsert.setInsdate(DateTime.getNowDateTime());
        productInsert.setSysuser(39);
        
       mr = productFacade.insertProductToDB(productInsert);
        
        //mhnhmata από το magaebean στην σελίδα
        if(mr==true){
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το προίόν δημιουργήθηκε επιτυχώς"));
            return "productAll";
        }   
        
        return "";
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public List<Photos> getPhotosList() {
        return photosList;
    }

    public void setPhotosList(List<Photos> photosList) {
        this.photosList = photosList;
    }

    public List<Produnit> getProdunits() {
        return produnits;
    }

    public void setProdunits(List<Produnit> produnits) {
        this.produnits = produnits;
    }
    
    public Produnit getProdunit() {
        return produnit;
    }

    public void setProdunit(Produnit produnit) {
        this.produnit = produnit;
    }
    
    public List<Prodcategory> getProdcategorys() {
        return prodcategorys;
    }

    public void setProdcategorys(List<Prodcategory> prodcategorys) {
        this.prodcategorys = prodcategorys;
    }

    
    public Prodcategory getProdcategory() {
        return prodcategory;
    }

    public void setProdcategory(Prodcategory prodcategory) {
        this.prodcategory = prodcategory;
    }

    
    public int getProdcategoryid() {
        return prodcategoryid;
    }

    public void setProdcategoryid(short prodcategoryid) {
        this.prodcategoryid = prodcategoryid;
    }
    
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public float getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(float buyprice) {
        this.buyprice = buyprice;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public float getSellprice() {
        return sellprice;
    }

    public void setSellprice(float sellprice) {
        this.sellprice = sellprice;
    }

    public CustvendFacade getCustvendFacade() {
        return custvendFacade;
    }

    public void setCustvendFacade(CustvendFacade custvendFacade) {
        this.custvendFacade = custvendFacade;
    }

    public List<Custvend> getCustvends() {
        return custvends;
    }

    public void setCustvends(List<Custvend> custvends) {
        this.custvends = custvends;
    }

    public Custvend getCustvend() {
        return custvend;
    }

    public void setCustvend(Custvend custvend) {
        this.custvend = custvend;
    }


    
}
