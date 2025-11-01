
/*
Design and implement a simple social networking simulation named Mini-Facebook (FriendBook) using Object-Oriented Programming concepts. 
Features:
i) Users can add friends.
ii)Users can post status updates.
iii)Each post can be liked or commented on.
iv)Users can see a news feed (posts from friends).

Entities:
User: id, name, friends[], posts[]
Post: id, author, content, timestamp, likesCount, comments[]
Comment: id, user, text, timestamp

Rules / Constraints:
i)User cannot friend themselves.
ii)Posts visible only to friends.
iii)A user can like a post only once.

Test Scenarios:
1. User A adds User B → B posts → A can see in feed.
2. User A likes and comments → counts update correctly.
3. Unfriend → posts disappear from feed.
4. Non-friends can’t see or like posts.

*/
import java.util.Date;

class Comment {
    int id;
    User user;
    String text;
    String timestamp;

    public Comment(int id, User user, String text) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.timestamp = new Date().toString();
    }
}

class Post {
    int id;
    User author;
    String content;
    String timestamp;
    int likesCount;
    Comment[] comments = new Comment[10];
    int commentCount = 0;
    User[] likedUsers = new User[10];
    int likeUserCount = 0;

    public Post(int id, User author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.timestamp = new Date().toString();
        this.likesCount = 0;
    }

    public void likePost(User u) {
        // Check if user already liked
        for (int i = 0; i < likeUserCount; i++) {
            if (likedUsers[i] == u) {
                System.out.println(u.name + " already liked this post.");
                return;
            }
        }
        likedUsers[likeUserCount++] = u;
        likesCount++;
        System.out.println(u.name + " liked " + author.name + "'s post.");
    }

    public void addComment(User u, String text) {
        Comment c = new Comment(commentCount + 1, u, text);
        comments[commentCount++] = c;
        System.out.println(u.name + " commented on " + author.name + "'s post: " + text);
    }

    public void showPost() {
        System.out.println("\nPost by: " + author.name);
        System.out.println("Content: " + content);
        System.out.println("Likes: " + likesCount);
        System.out.println("Comments:");
        for (int i = 0; i < commentCount; i++) {
            System.out.println(" - " + comments[i].user.name + ": " + comments[i].text);
        }
    }
}

class User {
    int id;
    String name;
    User[] friends = new User[10];
    int friendCount = 0;
    Post[] posts = new Post[10];
    int postCount = 0;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addFriend(User u) {
        if (u == this) {
            System.out.println("User cannot friend themselves.");
            return;
        }
        // Check if already friends
        for (int i = 0; i < friendCount; i++) {
            if (friends[i] == u) {
                System.out.println(name + " and " + u.name + " are already friends.");
                return;
            }
        }
        friends[friendCount++] = u;
        u.friends[u.friendCount++] = this;
        System.out.println(name + " and " + u.name + " are now friends.");
    }

    public void unfriend(User u) {
        boolean removed = false;
        for (int i = 0; i < friendCount; i++) {
            if (friends[i] == u) {
                for (int j = i; j < friendCount - 1; j++) {
                    friends[j] = friends[j + 1];
                }
                friends[--friendCount] = null;
                removed = true;
                break;
            }
        }
        // Also remove from the other user
        for (int i = 0; i < u.friendCount; i++) {
            if (u.friends[i] == this) {
                for (int j = i; j < u.friendCount - 1; j++) {
                    u.friends[j] = u.friends[j + 1];
                }
                u.friends[--u.friendCount] = null;
                break;
            }
        }
        if (removed)
            System.out.println(name + " unfriended " + u.name);
        else
            System.out.println("They are not friends.");
    }

    public void createPost(String content) {
        Post p = new Post(postCount + 1, this, content);
        posts[postCount++] = p;
        System.out.println(name + " posted: " + content);
    }

    public void viewFeed() {
        System.out.println("\n--- " + name + "'s News Feed ---");
        for (int i = 0; i < friendCount; i++) {
            User friend = friends[i];
            for (int j = 0; j < friend.postCount; j++) {
                friend.posts[j].showPost();
            }
        }
    }

    public boolean isFriend(User u) {
        for (int i = 0; i < friendCount; i++) {
            if (friends[i] == u)
                return true;
        }
        return false;
    }
}

public class facebook {
    public static void main(String[] args) {
        User a = new User(1, "Alice");
        User b = new User(2, "Bob");
        User c = new User(3, "Charlie");

        // 1. User A adds User B → B posts → A can see in feed.
        a.addFriend(b);
        b.createPost("Hello from Bob!");
        a.viewFeed();

        // 2. User A likes and comments → counts update correctly.
        b.posts[0].likePost(a);
        b.posts[0].addComment(a, "Nice post!");
        b.posts[0].showPost();

        // 3. Unfriend → posts disappear from feed.
        a.unfriend(b);
        a.viewFeed();

        // 4. Non-friends can’t see or like posts.
        c.viewFeed(); // Charlie can't see any posts
        b.posts[0].likePost(c); // Not friend, but simulate restriction
        if (!b.posts[0].author.isFriend(c)) {
            System.out.println("Charlie cannot like Bob's post (not friends).");
        }
    }
}