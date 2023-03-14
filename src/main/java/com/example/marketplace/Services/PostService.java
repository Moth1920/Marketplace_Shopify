package com.example.marketplace.Services;

import com.example.marketplace.Dto.PostCommentCreateDTO;
import com.example.marketplace.Dto.PostCreateDTO;
import com.example.marketplace.Entities.*;
import com.example.marketplace.Repositories.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.time.Instant;
import java.util.*;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private PostCommentRepository postCommentRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private PostCommentLikeRepository postCommentLikeRepository;


    // les postes
    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post addPost(PostCreateDTO postCreateDTO) {
        Optional<User> user = userService.getUserById(postCreateDTO.getUserId());

        String[] contentWords = postCreateDTO.getContent().split(" ");

        String[] filteredPost = filterUnallowedWords(contentWords);
        String newContent="";
        for(String w: filteredPost) {
            newContent+=w+" ";
        }

        Post post = new Post();
        post.setUser(user.get());
        post.setContent(newContent);
        post.setCreatedDate(Instant.now());

        return postRepository.save(post);
    }


    public Post updatePost(Post post, Long postId) {

        Post post1 = postRepository.findById(postId).get();

        if (Objects.nonNull(post.getContent()) && !"".equalsIgnoreCase(post.getContent())) {
            post1.setContent(post.getContent());
        }

        return  postRepository.save(post1);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }




    //Commentaire

    public PostComment commentPost(Long postId, PostCommentCreateDTO postCommentCreateDTO) {
        Optional<Post> post = postRepository.findById(postId);
        Optional<User> user = userService.getUserById(postCommentCreateDTO.getUserId());

        String[] contentWords = postCommentCreateDTO.getContent().split(" ");

        String[] filteredPost = filterUnallowedWords(contentWords);
        String newContent="";
        for(String w: filteredPost) {
            newContent+=w+" ";
        }

        PostComment postComment = new PostComment();
        postComment.setContent(newContent);

        user.get().getPostComments().add(postComment);
        post.get().getPostComments().add(postComment);

        postCommentRepository.save(postComment);

        return postComment;
    }


    public Iterable<PostComment> getPostComments() {
        return postCommentRepository.findAll();
    }

    public Optional<PostComment> getPostCommentById(Long id) {
        return postCommentRepository.findById(id);
    }



    //like Commentaire

    public PostCommentLike likeComment(Long commentId, Long userId) {
        Optional<PostComment> comment = postCommentRepository.findById(commentId);
        Optional<User> user = userService.getUserById(userId);

        PostCommentLike postCommentLike = new PostCommentLike();
        postCommentLike.setEtat(true);

        comment.get().getPostCommentLikes().add(postCommentLike);
        user.get().getPostCommentLikes().add(postCommentLike);

        comment.get().setLikesNumber(comment.get().getLikesNumber() + 1);

        return postCommentLikeRepository.save(postCommentLike);
    }
    public PostCommentLike disLikeComment(Long commentId, Long userId) {
        Optional<PostComment> comment = postCommentRepository.findById(commentId);
        Optional<User> user = userService.getUserById(userId);

        PostCommentLike postCommentLike = new PostCommentLike();
        postCommentLike.setEtat(false);

        comment.get().getPostCommentLikes().add(postCommentLike);
        user.get().getPostCommentLikes().add(postCommentLike);

        comment.get().setLikesNumber(comment.get().getLikesNumber() - 1);

        return postCommentLikeRepository.save(postCommentLike);
    }


    public Iterable<PostCommentLike> getPostCommentLikes() {
        return postCommentLikeRepository.findAll();
    }

    public Optional<PostCommentLike> getPostCommentLikeById(Long id) {
        return postCommentLikeRepository.findById(id);
    }





    //Raitingg
    public Rating ratePost(Long postId, Rating rating) {
        Optional<Post> post = postRepository.findById(postId);

        if(rating.getValue()>=0 && rating.getValue() <=5) {
            post.get().getRatings().add(rating);

            List<Double> ratings = new ArrayList<>();
            post.get().getRatings().forEach(r -> ratings.add(r.getValue()));
            double sum = ratings.stream().reduce(0.0, (a,b) -> a+b);
            post.get().setRatingAvg(sum/post.get().getRatings().size());

            return ratingRepository.save(rating);
        }
        return  rating;
    }
    public Iterable<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    public Optional<Rating> getRatingById(Long id) {
        return ratingRepository.findById(id);
    }


    // Like Post
    public PostLike likePost(Long postId, Long userId) {
        Optional<Post> post = postRepository.findById(postId);
        Optional<User> user = userService.getUserById(userId);

        PostLike postLike = new PostLike();
        postLike.setEtat(true);

        post.get().getPostLikes().add(postLike);
        user.get().getPostLikes().add(postLike);

        post.get().setLikesNumber(post.get().getLikesNumber() + 1); // incrémenter nombre de likes

        return postLikeRepository.save(postLike);
    }

    public PostLike disLikePost(Long postId, Long userId) {
        Optional<Post> post = postRepository.findById(postId);
        Optional<User> user = userService.getUserById(userId);

        boolean exists = Collections.disjoint(post.get().getPostLikes(),user.get().getPostLikes());


        if(!exists) {
            PostLike postLike = new PostLike();
            postLike.setEtat(false);

            post.get().getPostLikes().add(postLike);
            user.get().getPostLikes().add(postLike);
            post.get().setLikesNumber(post.get().getLikesNumber() - 1); // décrémenter nombre de likes
            return postLikeRepository.save(postLike);
        }

        else return new PostLike();
    }

    public Iterable<PostLike> getPostLikes() {
        return postLikeRepository.findAll();
    }

    public Optional<PostLike> getPostLikeById(Long id) {
        return postLikeRepository.findById(id);
    }


    public List<Post> topLikedPosts() {

        return postRepository.findTop3ByOrderByLikesNumberDesc();
    }





    public String[] filterUnallowedWords(String[] content) {
       // JSONParser parser = new JSONParser();
        JSONParser parser =new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("C:/Users/HP-Gaming/Desktop/final/Marketplace_Shopify/src/main/resources/static/not_allowed_words.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray wordsList = (JSONArray) jsonObject.get("BadWords");

            for (String word: content) {

                Iterator<Object> iterator = wordsList.iterator();

                while (iterator.hasNext()) {
                    if (word.equals(iterator.next())) {
                        for(int i=0; i<content.length; i++) {
                            content[i] = content[i].replace(word, "*****");
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }


}

