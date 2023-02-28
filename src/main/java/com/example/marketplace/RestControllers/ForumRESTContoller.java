package com.example.marketplace.RestControllers;


import com.example.marketplace.Entities.ProductComment;
import com.example.marketplace.Services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ForumRESTContoller {
    @Autowired
    ForumService forumService;

    @PostMapping("/addComment/{idUser}/{idProduit}")
    @ResponseBody
    public void assignCommentToUserAndProduct(@RequestBody ProductComment productComment,@PathVariable("idUser") Long idUser ,@PathVariable("idProduit") Long idProduit)
    {
        forumService.saveComment(productComment, idUser ,idProduit);
    }
    @DeleteMapping ("/delcomment/{id}")
    public void deleteComment(@PathVariable("id") Long idProductComment){
        forumService.deleteCommentById(idProductComment);
    }
}
