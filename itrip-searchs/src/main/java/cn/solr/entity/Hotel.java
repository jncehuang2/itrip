package cn.solr.entity;
import lombok.Getter;
import lombok.Setter;
import org.apache.solr.client.solrj.beans.Field;
import java.io.Serializable;

@Getter
@Setter
public class Hotel implements Serializable {

    @Field("id")
    private Long id;

    @Field("address")
    private String address;

    @Field
    private String hotelName;

}
