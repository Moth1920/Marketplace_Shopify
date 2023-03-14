package com.example.marketplace.RestControllers;

import com.example.marketplace.Entities.Boutique;
import com.example.marketplace.Entities.Categorie;
import com.example.marketplace.Entities.Produit;
import com.example.marketplace.Repositories.ProduitRepository;
import com.example.marketplace.Services.ProduitService;
import com.example.marketplace.Services.ProduitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProduitRESTContoller {
    @Autowired
    ProduitService produitService;
    @Autowired
    ProduitServiceImpl produitServiceImpl;

    Pageable pageable = PageRequest.of(0,2);





    @PostMapping("/CreateCategorie")
    @ResponseBody
    public Categorie createCategorie (@RequestBody Categorie categorie) {
        return produitService.saveCategorie(categorie);
    }
    @PostMapping("/CreateBou")
    @ResponseBody
    public Boutique createBoutique (@RequestBody Boutique boutique) {
        return produitService.saveBoutique(boutique);
    }


    @PutMapping("/Update/{idProduit}")
    @ResponseBody
    public Produit updateProduit(@RequestBody Produit produit,@PathParam("idProduit") Long idProduit) {
        return produitService.updateProduit(produit);
    }
    @PutMapping("/Update/{idbou}")
    @ResponseBody
    public Boutique updateBoutique(@RequestBody Boutique boutique,@PathParam("idbou") Long idbou) {
        return produitService.updateBoutique(boutique);
    }
    @PutMapping("/UpdateCategorie/{idCategorie}")
    @ResponseBody
    public Categorie updateCategorie(@RequestBody Categorie categorie,@PathParam("idCategorie") Long idCategorie) {
        return produitService.updateCategorie(categorie);
    }

    @DeleteMapping("/delete/{idProduit}")

    public void deleteProduit(@PathVariable("idProduit") Long idProduit)
    {
        produitService.deleteProduitById(idProduit);
    }
    @GetMapping(value="getAllProduits/{offset}/{pageSize}")
    public ResponseEntity<Page<Produit>> findAllProduits(@PathVariable int offset,@PathVariable int pageSize){
        Page<Produit> products = produitService.findAllProduits(offset, pageSize);
        return ResponseEntity.ok().body(products);
    }
    @GetMapping(value="getAllCategories")
    public List<Categorie> findAllCategories(){
        return produitServiceImpl.findAllCategories();
    }
    @PostMapping("/addproducttocat/{idCategorie}/{idUser}")
    @ResponseBody
    public void assignProductToCategorie(@RequestBody Produit produit,@PathVariable("idCategorie") Long idCategorie,@PathVariable("idUser") Long idUser )
    {
        produitService.affectprodtocat(produit, idCategorie, idUser);
    }
    @GetMapping("/quantity/{quantity}")
    public List<Produit> FindByQuantityLessThanEqual(@PathVariable ("quantity") int quantity) {
        return produitService.findByQuantityLessThanEqual(quantity);
    }

    @GetMapping("/alert")
    public String showAlert() {
        return produitService.showAlert();
    }
    @PostMapping("/addcattobou/{idbou}")
    @ResponseBody
    public void assignCategorieToBoutique(@RequestBody Categorie categorie,@PathVariable("idbou") Long idbou )
    {
        produitService.affectcattobou(categorie, idbou);
    }
    @GetMapping("/filter/{minPrixProduit}/{maxPrixProduit}")
    public List<Produit> filterProduit(@PathVariable ("minPrixProduit")float minPrixProduit, @PathVariable ("maxPrixProduit")float maxPrixProduit) {
        return produitService.filterProduit(minPrixProduit, maxPrixProduit);
    }
}



