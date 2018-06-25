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
import sessionsBeans.UploadImageFacade;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class ProductEditManage implements Serializable{
    
    @NotNull(message = "Παρακαλώ συμπληρώστε το όνομα του προϊόντος")  
    private String name;
    private String isactive;

    @NotNull(message = "Παρακαλώ συμπληρώστε την τιμή του προϊόντος") 
    private float buyprice;
    private String remarks;
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
    
    private Product product;
    
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
    
    
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
       
       
    @PostConstruct
    public void init() {
        
        custvends = custvendFacade.getAllCustvendVendorFromDB();
        prodcategorys = categoryFacade.getAllCategoriesWhereIsActive();
        photosList = uploadImageFacade.getAllfiles();
        produnits = productUnitFacade.getAllProdunit();
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        
        product =(Product) sessionMap.get("editProduct");
        System.out.println("22222"+product.getName());
        
        photos = product.getPhotosid();
        prodcategory = product.getProdcategoryid();
        custvend = product.getVendor();
        produnit = product.getProdunitid();
    }

    
    public String updateProduct(){
            
        boolean mr=false;
        
        Product producUpdate = new Product();
        
  
        
        producUpdate.setProductid(product.getProductid());
        producUpdate.setName(product.getName());
        producUpdate.setSlugname(ConvertToGreeklish.greek2Roman(product.getName()));
        
//        int isactiveInt;
//        if(isactive.equals("true")){
//            isactiveInt=1;
//        }else{
//            isactiveInt=0;
//        }

        producUpdate.setIsactive( 1);
        producUpdate.setIsvisible((short) 1);
        producUpdate.setProdcategoryid(prodcategory);
        producUpdate.setProdunitid(produnit);
        producUpdate.setBuyprice(product.getBuyprice());
        producUpdate.setSellprice(product.getSellprice());
        producUpdate.setVendor(custvend);
        producUpdate.setQty(product.getQty());
        producUpdate.setPhotosid(photos);
        producUpdate.setRemarks(product.getRemarks()); 
        producUpdate.setInsdate(DateTime.getNowDateTime());
        producUpdate.setSysuser(39);
        
        
        mr = productFacade.updateProductToDatabase(producUpdate);
        
        //mhnhmata από το magaebean στην σελίδα
        if(mr==true){
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Το προϊόν ανανεώθηκε επιτυχώς"));
            return "productAll";
        }   
        
        return "";
         
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Prodcategory getProdcategory() {
        return prodcategory;
    }

    public void setProdcategory(Prodcategory prodcategory) {
        this.prodcategory = prodcategory;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Prodcategory> getProdcategorys() {
        return prodcategorys;
    }

    public void setProdcategorys(List<Prodcategory> prodcategorys) {
        this.prodcategorys = prodcategorys;
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

    public int getProdcategoryid() {
        return prodcategoryid;
    }

    public void setProdcategoryid(int prodcategoryid) {
        this.prodcategoryid = prodcategoryid;
    }

    public Produnit getProdunit() {
        return produnit;
    }

    public void setProdunit(Produnit produnit) {
        this.produnit = produnit;
    }

    public List<Produnit> getProdunits() {
        return produnits;
    }

    public void setProdunits(List<Produnit> produnits) {
        this.produnits = produnits;
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

    public ProductFacade getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    public CategoryFacade getCategoryFacade() {
        return categoryFacade;
    }

    public void setCategoryFacade(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }

    public ProductUnitFacade getProductUnitFacade() {
        return productUnitFacade;
    }

    public void setProductUnitFacade(ProductUnitFacade productUnitFacade) {
        this.productUnitFacade = productUnitFacade;
    }

    public UploadImageFacade getUploadImageFacade() {
        return uploadImageFacade;
    }

    public void setUploadImageFacade(UploadImageFacade uploadImageFacade) {
        this.uploadImageFacade = uploadImageFacade;
    }

    public CustvendFacade getCustvendFacade() {
        return custvendFacade;
    }

    public void setCustvendFacade(CustvendFacade custvendFacade) {
        this.custvendFacade = custvendFacade;
    }

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }
    
    
    
}
