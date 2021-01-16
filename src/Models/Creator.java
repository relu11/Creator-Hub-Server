package Models;

import Controllers.Database;

import java.util.ArrayList;
import java.util.HashMap;

public class Creator extends User implements Observer {
    private double moneyEarned;
    private ArrayList<User> followersList;
    private ArrayList<Post> posts;
    private double subscriptionPrice;
    private String bankAccount;
    private ArrayList<Chat> chats;

    public Creator() {
    }

    public Creator(double moneyEarned, ArrayList<User> followersList, ArrayList<Post> posts, double subscriptionPrice, String bankAccount, ArrayList<Chat> chats) {
        this.moneyEarned = moneyEarned;
        this.followersList = followersList;
        this.posts = posts;
        this.subscriptionPrice = subscriptionPrice;
        this.bankAccount = bankAccount;
        this.chats = chats;
    }

    public Creator(String name, String country, String email, double moneyEarned, ArrayList<User> followersList, ArrayList<Post> posts, double subscriptionPrice, String bankAccount, ArrayList<Chat> chats) {
        super(name, country, email);
        this.moneyEarned = moneyEarned;
        this.followersList = followersList;
        this.posts = posts;
        this.subscriptionPrice = subscriptionPrice;
        this.bankAccount = bankAccount;
        this.chats = chats;
    }

    public Creator(String id, String name, String country, String email, boolean banned, ArrayList<String> warnings, double moneyEarned, ArrayList<User> followersList, ArrayList<Post> posts, double subscriptionPrice, String bankAccount, ArrayList<Chat> chats) {
        super(id, name, country, email, banned, warnings);
        this.moneyEarned = moneyEarned;
        this.followersList = followersList;
        this.posts = posts;
        this.subscriptionPrice = subscriptionPrice;
        this.bankAccount = bankAccount;
        this.chats = chats;
    }
    public Creator(String name, String country, String email){
        super(name, country, email);

    }
    public double getMoneyEarned() {
        return moneyEarned;
    }

    public void setMoneyEarned(double moneyEarned) {
        this.moneyEarned = moneyEarned;
    }

    public ArrayList<User> getFollowersList() {
        return followersList;
    }

    public void setFollowersList(ArrayList<User> followersList) {
        this.followersList = followersList;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public double getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public void setSubscriptionPrice(double subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    public void addPost(Post post) {
        Database database = Database.getCurrentDatabase();
        database.insertObject("Posts", post);
    }

    public void deletePost(Post post) {
        Database database = Database.getCurrentDatabase();
        database.deleteObject("Posts", post, "id", post.getId());
    }

    public int getLikesCount() {
        // TODO: Update later
        return 0;
    }

    public HashMap<String, Double> getAnalytics() {
        // TODO: Update later
        return new HashMap<>();
    }

    public void withDrawMoney(double amount) {
        System.out.println("Withdrawing Money");
        if (amount <= this.moneyEarned && amount > 0) {
            this.moneyEarned -= amount;
            System.out.println("Money withdrawn successfully");
        } else {
            // TODO: Add error handling instead
            System.out.println("Invalid value entered");
        }
    }

    public void addChat(Chat chat) {
        this.chats.add(chat);
        Database database = Database.getCurrentDatabase();
        database.updateObject("Users", this, "id", this.getId());
    }

    @Override
    public void update() {
        Database database = Database.getCurrentDatabase();
        Creator updatedObject = (Creator) database.getDocument("Users", "id", this.getId(), Creator.class);
        this.chats = updatedObject.chats;
        System.out.println( "you have a new message");
    }

}
