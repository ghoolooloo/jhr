package jo.jhr.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import jo.jhr.domain.enumeration.CarouselStatus;

/**
 * A DTO for the {@link jo.jhr.domain.Carousel} entity.
 */
@ApiModel(description = "轮播\n@author Jo")
public class CarouselDTO implements Serializable {
    
    private Long id;

    /**
     * 大图
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "大图", required = true)
    private String picture;

    /**
     * 轮播的顺序
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 999999999)
    @ApiModelProperty(value = "轮播的顺序", required = true)
    private Integer sort;

    @NotNull
    private CarouselStatus status;

    @NotNull
    @Size(max = 20)
    private String createdBy;

    @NotNull
    private Instant createdDate;

    private Instant lastModifiedDate;

    @Size(max = 20)
    private String lastModifiedBy;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public CarouselStatus getStatus() {
        return status;
    }

    public void setStatus(CarouselStatus status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarouselDTO carouselDTO = (CarouselDTO) o;
        if (carouselDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), carouselDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CarouselDTO{" +
            "id=" + getId() +
            ", picture='" + getPicture() + "'" +
            ", sort=" + getSort() +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
