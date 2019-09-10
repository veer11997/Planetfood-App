/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package planetfood.pojo;

/**
 *
 * @author hp
 */
public class Category {

    public String getCatId() {
        return CatId;
    }

    public void setCatId(String CatId) {
        this.CatId = CatId;
    }

    public String getCatName() {
        return CatName;
    }

    public void setCatName(String CatName) {
        this.CatName = CatName;
    }
    
    private String CatId;
    private String CatName;
    
    
}
