package berfin.songlib.mappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LastFmResponse {

    private Track track;

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Track {
        private String name;
        private String mbid;
        private String url;
        private String duration;
        private Streamable streamable;
        private String listeners;
        private String playcount;
        private Artist artist;
        private Album album;
        private TopTags toptags;
        private Wiki wiki;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMbid() {
            return mbid;
        }

        public void setMbid(String mbid) {
            this.mbid = mbid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public Streamable getStreamable() {
            return streamable;
        }

        public void setStreamable(Streamable streamable) {
            this.streamable = streamable;
        }

        public String getListeners() {
            return listeners;
        }

        public void setListeners(String listeners) {
            this.listeners = listeners;
        }

        public String getPlaycount() {
            return playcount;
        }

        public void setPlaycount(String playcount) {
            this.playcount = playcount;
        }

        public Artist getArtist() {
            return artist;
        }

        public void setArtist(Artist artist) {
            this.artist = artist;
        }

        public Album getAlbum() {
            return album;
        }

        public void setAlbum(Album album) {
            this.album = album;
        }

        public TopTags getToptags() {
            return toptags;
        }

        public void setToptags(TopTags toptags) {
            this.toptags = toptags;
        }

        public Wiki getWiki() {
            return wiki;
        }

        public void setWiki(Wiki wiki) {
            this.wiki = wiki;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Streamable {
            @JsonProperty("#text")
            private String text;
            private String fulltrack;

            // Getters and Setters
            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getFulltrack() {
                return fulltrack;
            }

            public void setFulltrack(String fulltrack) {
                this.fulltrack = fulltrack;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Artist {
            private String name;
            private String mbid;
            private String url;

            // Getters and Setters
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMbid() {
                return mbid;
            }

            public void setMbid(String mbid) {
                this.mbid = mbid;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Album {
            private String artist;
            private String title;
            private String mbid;
            private String url;
            private List<Image> image;

            // Getters and Setters
            public String getArtist() {
                return artist;
            }

            public void setArtist(String artist) {
                this.artist = artist;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMbid() {
                return mbid;
            }

            public void setMbid(String mbid) {
                this.mbid = mbid;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public List<Image> getImage() {
                return image;
            }

            public void setImage(List<Image> image) {
                this.image = image;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Image {
                @JsonProperty("#text")
                private String text;
                private String size;

                // Getters and Setters
                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class TopTags {
            private List<Tag> tag;

            // Getters and Setters
            public List<Tag> getTag() {
                return tag;
            }

            public void setTag(List<Tag> tag) {
                this.tag = tag;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Tag {
                private String name;
                private String url;

                // Getters and Setters
                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Wiki {
            private String published;
            private String summary;
            private String content;

            // Getters and Setters
            public String getPublished() {
                return published;
            }

            public void setPublished(String published) {
                this.published = published;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
