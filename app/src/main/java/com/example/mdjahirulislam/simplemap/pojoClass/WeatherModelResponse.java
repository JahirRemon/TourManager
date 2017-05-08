package com.example.mdjahirulislam.simplemap.pojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mdjahirulislam on 19/04/17.
 */

public class WeatherModelResponse {

    @SerializedName("query")
    @Expose
    private Query query;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public static class Query {

        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("lang")
        @Expose
        private String lang;
        @SerializedName("results")
        @Expose
        private Results results;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public Results getResults() {
            return results;
        }

        public void setResults(Results results) {
            this.results = results;
        }


        public static class Results {

            @SerializedName("channel")
            @Expose
            private Channel channel;

            public Channel getChannel() {
                return channel;
            }

            public void setChannel(Channel channel) {
                this.channel = channel;
            }


            public static class Channel {

                @SerializedName("units")
                @Expose
                private Units units;
                @SerializedName("title")
                @Expose
                private String title;
                @SerializedName("link")
                @Expose
                private String link;
                @SerializedName("description")
                @Expose
                private String description;
                @SerializedName("language")
                @Expose
                private String language;
                @SerializedName("lastBuildDate")
                @Expose
                private String lastBuildDate;
                @SerializedName("ttl")
                @Expose
                private String ttl;
                @SerializedName("location")
                @Expose
                private Location location;
                @SerializedName("wind")
                @Expose
                private Wind wind;
                @SerializedName("atmosphere")
                @Expose
                private Atmosphere atmosphere;
                @SerializedName("astronomy")
                @Expose
                private Astronomy astronomy;
                @SerializedName("image")
                @Expose
                private Image image;
                @SerializedName("item")
                @Expose
                private Item item;

                public Units getUnits() {
                    return units;
                }

                public void setUnits(Units units) {
                    this.units = units;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getLanguage() {
                    return language;
                }

                public void setLanguage(String language) {
                    this.language = language;
                }

                public String getLastBuildDate() {
                    return lastBuildDate;
                }

                public void setLastBuildDate(String lastBuildDate) {
                    this.lastBuildDate = lastBuildDate;
                }

                public String getTtl() {
                    return ttl;
                }

                public void setTtl(String ttl) {
                    this.ttl = ttl;
                }

                public Location getLocation() {
                    return location;
                }

                public void setLocation(Location location) {
                    this.location = location;
                }

                public Wind getWind() {
                    return wind;
                }

                public void setWind(Wind wind) {
                    this.wind = wind;
                }

                public Atmosphere getAtmosphere() {
                    return atmosphere;
                }

                public void setAtmosphere(Atmosphere atmosphere) {
                    this.atmosphere = atmosphere;
                }

                public Astronomy getAstronomy() {
                    return astronomy;
                }

                public void setAstronomy(Astronomy astronomy) {
                    this.astronomy = astronomy;
                }

                public Image getImage() {
                    return image;
                }

                public void setImage(Image image) {
                    this.image = image;
                }

                public Item getItem() {
                    return item;
                }

                public void setItem(Item item) {
                    this.item = item;
                }


                public static class Units {

                    @SerializedName("distance")
                    @Expose
                    private String distance;
                    @SerializedName("pressure")
                    @Expose
                    private String pressure;
                    @SerializedName("speed")
                    @Expose
                    private String speed;
                    @SerializedName("temperature")
                    @Expose
                    private String temperature;

                    public String getDistance() {
                        return distance;
                    }

                    public void setDistance(String distance) {
                        this.distance = distance;
                    }

                    public String getPressure() {
                        return pressure;
                    }

                    public void setPressure(String pressure) {
                        this.pressure = pressure;
                    }

                    public String getSpeed() {
                        return speed;
                    }

                    public void setSpeed(String speed) {
                        this.speed = speed;
                    }

                    public String getTemperature() {
                        return temperature;
                    }

                    public void setTemperature(String temperature) {
                        this.temperature = temperature;
                    }

                }

                public static class Location {

                    @SerializedName("city")
                    @Expose
                    private String city;
                    @SerializedName("country")
                    @Expose
                    private String country;
                    @SerializedName("region")
                    @Expose
                    private String region;

                    public String getCity() {
                        return city;
                    }

                    public void setCity(String city) {
                        this.city = city;
                    }

                    public String getCountry() {
                        return country;
                    }

                    public void setCountry(String country) {
                        this.country = country;
                    }

                    public String getRegion() {
                        return region;
                    }

                    public void setRegion(String region) {
                        this.region = region;
                    }

                }

                public static class Wind {

                    @SerializedName("chill")
                    @Expose
                    private String chill;
                    @SerializedName("direction")
                    @Expose
                    private String direction;
                    @SerializedName("speed")
                    @Expose
                    private String speed;

                    public String getChill() {
                        return chill;
                    }

                    public void setChill(String chill) {
                        this.chill = chill;
                    }

                    public String getDirection() {
                        return direction;
                    }

                    public void setDirection(String direction) {
                        this.direction = direction;
                    }

                    public String getSpeed() {
                        return speed;
                    }

                    public void setSpeed(String speed) {
                        this.speed = speed;
                    }

                }

                public static class Atmosphere {

                    @SerializedName("humidity")
                    @Expose
                    private String humidity;
                    @SerializedName("pressure")
                    @Expose
                    private String pressure;
                    @SerializedName("rising")
                    @Expose
                    private String rising;
                    @SerializedName("visibility")
                    @Expose
                    private String visibility;

                    public String getHumidity() {
                        return humidity;
                    }

                    public void setHumidity(String humidity) {
                        this.humidity = humidity;
                    }

                    public String getPressure() {
                        return pressure;
                    }

                    public void setPressure(String pressure) {
                        this.pressure = pressure;
                    }

                    public String getRising() {
                        return rising;
                    }

                    public void setRising(String rising) {
                        this.rising = rising;
                    }

                    public String getVisibility() {
                        return visibility;
                    }

                    public void setVisibility(String visibility) {
                        this.visibility = visibility;
                    }

                }

                public static class Astronomy {

                    @SerializedName("sunrise")
                    @Expose
                    private String sunrise;
                    @SerializedName("sunset")
                    @Expose
                    private String sunset;

                    public String getSunrise() {
                        return sunrise;
                    }

                    public void setSunrise(String sunrise) {
                        this.sunrise = sunrise;
                    }

                    public String getSunset() {
                        return sunset;
                    }

                    public void setSunset(String sunset) {
                        this.sunset = sunset;
                    }

                }

                public static class Image {

                    @SerializedName("title")
                    @Expose
                    private String title;
                    @SerializedName("width")
                    @Expose
                    private String width;
                    @SerializedName("height")
                    @Expose
                    private String height;
                    @SerializedName("link")
                    @Expose
                    private String link;
                    @SerializedName("url")
                    @Expose
                    private String url;

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getWidth() {
                        return width;
                    }

                    public void setWidth(String width) {
                        this.width = width;
                    }

                    public String getHeight() {
                        return height;
                    }

                    public void setHeight(String height) {
                        this.height = height;
                    }

                    public String getLink() {
                        return link;
                    }

                    public void setLink(String link) {
                        this.link = link;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                }

                public static class Item {

                    @SerializedName("title")
                    @Expose
                    private String title;
                    @SerializedName("lat")
                    @Expose
                    private String lat;
                    @SerializedName("long")
                    @Expose
                    private String _long;
                    @SerializedName("link")
                    @Expose
                    private String link;
                    @SerializedName("pubDate")
                    @Expose
                    private String pubDate;
                    @SerializedName("condition")
                    @Expose
                    private Condition condition;
                    @SerializedName("forecast")
                    @Expose
                    private List<Forecast> forecast = null;
                    @SerializedName("description")
                    @Expose
                    private String description;
                    @SerializedName("guid")
                    @Expose
                    private Guid guid;

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getLat() {
                        return lat;
                    }

                    public void setLat(String lat) {
                        this.lat = lat;
                    }

                    public String getLong() {
                        return _long;
                    }

                    public void setLong(String _long) {
                        this._long = _long;
                    }

                    public String getLink() {
                        return link;
                    }

                    public void setLink(String link) {
                        this.link = link;
                    }

                    public String getPubDate() {
                        return pubDate;
                    }

                    public void setPubDate(String pubDate) {
                        this.pubDate = pubDate;
                    }

                    public Condition getCondition() {
                        return condition;
                    }

                    public void setCondition(Condition condition) {
                        this.condition = condition;
                    }

                    public List<Forecast> getForecast() {
                        return forecast;
                    }

                    public void setForecast(List<Forecast> forecast) {
                        this.forecast = forecast;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public Guid getGuid() {
                        return guid;
                    }

                    public void setGuid(Guid guid) {
                        this.guid = guid;
                    }


                    public static class Condition {

                        @SerializedName("code")
                        @Expose
                        private String code;
                        @SerializedName("date")
                        @Expose
                        private String date;
                        @SerializedName("temp")
                        @Expose
                        private String temp;
                        @SerializedName("text")
                        @Expose
                        private String text;

                        public String getCode() {
                            return code;
                        }

                        public void setCode(String code) {
                            this.code = code;
                        }

                        public String getDate() {
                            return date;
                        }

                        public void setDate(String date) {
                            this.date = date;
                        }

                        public String getTemp() {
                            return temp;
                        }

                        public void setTemp(String temp) {
                            this.temp = temp;
                        }

                        public String getText() {
                            return text;
                        }

                        public void setText(String text) {
                            this.text = text;
                        }

                    }

                    public static class Forecast {

                        @SerializedName("code")
                        @Expose
                        private String code;
                        @SerializedName("date")
                        @Expose
                        private String date;
                        @SerializedName("day")
                        @Expose
                        private String day;
                        @SerializedName("high")
                        @Expose
                        private String high;
                        @SerializedName("low")
                        @Expose
                        private String low;
                        @SerializedName("text")
                        @Expose
                        private String text;

                        public String getCode() {
                            return code;
                        }

                        public void setCode(String code) {
                            this.code = code;
                        }

                        public String getDate() {
                            return date;
                        }

                        public void setDate(String date) {
                            this.date = date;
                        }

                        public String getDay() {
                            return day;
                        }

                        public void setDay(String day) {
                            this.day = day;
                        }

                        public String getHigh() {
                            return high;
                        }

                        public void setHigh(String high) {
                            this.high = high;
                        }

                        public String getLow() {
                            return low;
                        }

                        public void setLow(String low) {
                            this.low = low;
                        }

                        public String getText() {
                            return text;
                        }

                        public void setText(String text) {
                            this.text = text;
                        }

                    }

                    public static class Guid {

                        @SerializedName("isPermaLink")
                        @Expose
                        private String isPermaLink;

                        public String getIsPermaLink() {
                            return isPermaLink;
                        }

                        public void setIsPermaLink(String isPermaLink) {
                            this.isPermaLink = isPermaLink;
                        }

                    }

                }
            }

        }

    }

}
