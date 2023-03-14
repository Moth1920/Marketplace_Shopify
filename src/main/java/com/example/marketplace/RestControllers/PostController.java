package com.example.marketplace.RestControllers;
import java.util.List;

import com.example.marketplace.Dto.PostCommentCreateDTO;
import com.example.marketplace.Dto.PostCreateDTO;
import com.example.marketplace.Entities.*;
import com.example.marketplace.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;




    //Les Posts

    @PostMapping("/addPost")
    public Post addPost(@RequestBody PostCreateDTO postCreateDTO) {
        return postService.addPost(postCreateDTO);
    }

    @PutMapping("/updatePost/{postId}")
    public Post updatePost(@RequestBody Post post, @PathVariable("postId") long postId) {
        return postService.updatePost(post, postId);
    }
    @DeleteMapping("/supp/{postId}")
    public void deletePost(@PathVariable("postId") long postId) {
        postService.deletePost(postId);
    }

    @GetMapping("/allPost")
    public Iterable<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("postby/{id}")
    public Optional<Post> getPostById(@PathVariable("id") long id) {
        return postService.getPostById(id);
    }







    //PostLikeee

    @PostMapping("/{postId}/like")
    public void likePost(@PathVariable("postId") long postId, @RequestBody User user) {
        postService.likePost(postId, user.getIdUser());
    }

    @PostMapping("/{postId}/dislike")
    public void disLikePost(@PathVariable("postId") long postId, @RequestBody User user) {
        postService.disLikePost(postId, user.getIdUser());
    }
    @GetMapping("/allPostlikes")
    public Iterable<PostLike> getPostLikes() {
        return postService.getPostLikes();
    }

    @GetMapping("/postlike-by/{id}")
    public Optional<PostLike> getPostLikeById(@PathVariable("id") long id) {
        return postService.getPostLikeById(id);
    }

    @GetMapping("/top-liked")
    public List<Post> topLikedPosts() {
        return postService.topLikedPosts();
    }


    //Les commentaires


    @PostMapping("/{postId}/addComment")
    public PostComment commentPost(@PathVariable("postId") long postId, @RequestBody PostCommentCreateDTO postCommentCreateDTO) {
        return postService.commentPost(postId, postCommentCreateDTO);
    }

    @GetMapping("/allComment")
    public Iterable<PostComment> getPostComments() {
        return postService.getPostComments();
    }

    @GetMapping("comment/{id}")
    public Optional<PostComment> getPostCommentById(@PathVariable("id") long id) {
        return postService.getPostCommentById(id);
    }

    //Les likes comment

    @PostMapping("/{commentId}/likeComment")
    public void likePostComment(@PathVariable("commentId") Long commentId, @RequestBody User user) {
        postService.likeComment(commentId, user.getIdUser());
    }

    @PostMapping("/{commentId}/dislikeComment")
    public void disLikePostComment(@PathVariable("commentId") Long commentId, @RequestBody User user) {
        postService.disLikeComment(commentId, user.getIdUser());
    }

    @GetMapping("/allCommentLike")
    public Iterable<PostCommentLike> getPostCommentLikes() {
        return postService.getPostCommentLikes();
    }

    @GetMapping("likeComment/{id}")
    public Optional<PostCommentLike> getPostCommentLikeById(@PathVariable("id") long id) {
        return postService.getPostCommentLikeById(id);
    }


    //Raiting

    @PostMapping("/{postId}/addRate")
    public Rating ratePost(@PathVariable("postId") long postId, @RequestBody Rating rating) {
        return postService.ratePost(postId, rating);
    }
    @GetMapping("ratings/all")
    public Iterable<Rating> getRatings() {
        return postService.getRatings();
    }

    @GetMapping("rating-by/{id}")
    public Optional<Rating> getRatingById(@PathVariable("id") long id) {
        return postService.getRatingById(id);
    }








}
