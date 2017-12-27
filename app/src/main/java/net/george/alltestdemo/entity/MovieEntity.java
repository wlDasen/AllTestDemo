package net.george.alltestdemo.entity;

import android.util.Log;

import java.util.List;

/**
 * @author George
 * @date 2017/12/27
 * @email georgejiapeidi@gmail.com
 * @describe GSON数据类for Retrofit+Rxjava+Okhttp
 */
public class MovieEntity {
    private int count;
    private int start;
    private int total;
    private List<Subjects> subjects;
    private String title;

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public List<Subjects> getSubjects() {
        return subjects;
    }
    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[MovieEntity]count:" + count + ",start:" + start + ",total:" + total + ",title:" + title);
        for (Subjects subject : subjects) {
            stringBuffer.append(subject);
        }
        return stringBuffer.toString();
    }

    private class Subjects {
        private Rating rating;
        private List<String> genres;
        private String title;
        private List<Casts> casts;
        private int collect_count;
        private String original_title;
        private String subtype;
        private List<Directors> directors;
        private String year;
        private Images images;
        private String alt;
        private String  id;

        public List<String> getGenres() {
            return genres;
        }
        public void setGenres(List<String> genres) {
            this.genres = genres;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public int getCollect_count() {
            return collect_count;
        }
        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }
        public String getOriginal_title() {
            return original_title;
        }
        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }
        public String getSubtype() {
            return subtype;
        }
        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }
        public String getYear() {
            return year;
        }
        public void setYear(String year) {
            this.year = year;
        }
        public String getAlt() {
            return alt;
        }
        public void setAlt(String alt) {
            this.alt = alt;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public List<Casts> getCasts() {
            return casts;
        }
        public void setCasts(List<Casts> casts) {
            this.casts = casts;
        }
        public List<Directors> getDirectors() {
            return directors;
        }
        public void setDirectors(List<Directors> directors) {
            this.directors = directors;
        }
        public Rating getRating() {
            return rating;
        }
        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public Images getImages() {
            return images;
        }

        public void setImages(Images images) {
            this.images = images;
        }

        @Override
        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[Subject]title:" + title + ",collect_count:" + collect_count + ",original_title:" + original_title +
                ",subtype:" + subtype + ",year:" + year + ",alt:" + alt + ",id:" + id);
            stringBuffer.append("rating:" + rating + "images:" + images);
            stringBuffer.append(genres);
            for (Casts cast : casts) {
                stringBuffer.append(cast);
            }
            for (Directors director : directors) {
                stringBuffer.append(director);
            }
            stringBuffer.append(images);
            return stringBuffer.toString();
        }
    }
    private class Rating {
        private int max;
        private float average;
        private String starts;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public float getAverage() {
            return average;
        }

        public void setAverage(int average) {
            this.average = average;
        }

        public String getStarts() {
            return starts;
        }

        public void setStarts(String starts) {
            this.starts = starts;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        @Override
        public String toString() {
            return "[Rating]max:" + max + ",average:" + average + ",starts:" + starts + ",min:" + min;
        }
    }
    private class Casts {
        private String alt;
        private Avatars avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }
        public void setAlt(String alt) {
            this.alt = alt;
        }
        public Avatars getAvatars() {
            return avatars;
        }
        public void setAvatars(Avatars avatars) {
            this.avatars = avatars;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[Casts]alt:" + alt + "id:" + id + ",name:" + name + ",avatars:" + avatars);
            return stringBuffer.toString();
        }
    }
    private class Avatars {
        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        @Override
        public String toString() {
            return "[Avatars]small:" + small + ",large:" + large + ",medium:" + medium;
        }
    }
    private class Directors {
        private String alt;
        private Avatars avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }
        public void setAlt(String alt) {
            this.alt = alt;
        }
        public Avatars getAvatars() {
            return avatars;
        }
        public void setAvatars(Avatars avatars) {
            this.avatars = avatars;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return ("[Casts]alt:" + alt + "id:" + id + ",name:" + name + ",avatars:" + avatars);
        }
    }
    private class Images {
        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }
        public void setSmall(String small) {
            this.small = small;
        }
        public String getLarge() {
            return large;
        }
        public void setLarge(String large) {
            this.large = large;
        }
        public String getMedium() {
            return medium;
        }
        public void setMedium(String medium) {
            this.medium = medium;
        }

        @Override
        public String toString() {
            return "[Avatars]small:" + small + ",large:" + large + ",medium:" + medium;
        }
    }
}
