package example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.min;

public class Recommend {
    private Map<Double, String> computeNearestNeighbor(String username, List<User> users) {
        Map<Double, String> distances = new TreeMap<>();
        User u1 = new User(username);
        for (User user:users) {
            if (username.equals(user.userName)) {
                u1 = user;
            }
        }
        for (int i = 0; i < users.size(); i++) {
            User u2 = users.get(i);

            if (!u2.userName.equals(username)) {
                double distance = pearson_dis(u2.courseList, u1.courseList);
                distances.put(distance, u2.userName);
            }

        }
        System.out.println("该用户与其他用户的皮尔森相关系数 -> " + distances);
        return distances;
    }

    private double pearson_dis(List<Course> rating1, List<Course> rating2) {
        int n = min(rating1.size(),rating2.size());
        List<Integer> rating1ScoreCollect = rating1.stream().map(A -> A.appraise).collect(Collectors.toList());
        List<Integer> rating2ScoreCollect = rating2.stream().map(A -> A.appraise).collect(Collectors.toList());
        System.out.println(rating2ScoreCollect);
        double Ex = rating1ScoreCollect.stream().mapToDouble(x -> x).sum();
        double Ey = rating2ScoreCollect.stream().mapToDouble(y -> y).sum();
        double Ex2 = rating1ScoreCollect.stream().mapToDouble(x -> Math.pow(x, 2)).sum();
        double Ey2 = rating2ScoreCollect.stream().mapToDouble(y -> Math.pow(y, 2)).sum();
        double Exy = IntStream.range(0, n).mapToDouble(i -> rating1ScoreCollect.get(i) * rating2ScoreCollect.get(i)).sum();
        double numerator = Exy - Ex * Ey / n;
        double denominator = Math.sqrt((Ex2 - Math.pow(Ex, 2) / n) * (Ey2 - Math.pow(Ey, 2) / n));
        if (denominator == 0) return 0.0;
        return numerator / denominator;
    }

    public List<Course> recommend(String username, List<User> users) {
        //找到最近邻
        Map<Double, String> distances = computeNearestNeighbor(username, users);
        String nearest = distances.values().iterator().next();
        System.out.println("最近邻 -> " + nearest);

        //找到最近邻选过，但是我们没选过的课，计算推荐
        User neighborRatings = new User();
        for (User user:users) {
            if (nearest.equals(user.userName)) {
                neighborRatings = user;
            }
        }
        System.out.println("最近邻选过的课 -> " + neighborRatings.courseList);

        User userRatings = new User();
        for (User user:users) {
            if (username.equals(user.userName)) {
                userRatings = user;
            }
        }
        System.out.println("用户选过的课 -> " + userRatings.courseList);

        //根据自己和邻居的电影计算推荐的电影
        List<Course> recommendationMovies = new ArrayList<>();
        for (Course movie : neighborRatings.courseList) {
            if (userRatings.find(movie.cName) == null) {
                recommendationMovies.add(movie);
            }
        }
        Collections.sort(recommendationMovies);
        return recommendationMovies;
    }
}
