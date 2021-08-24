package commons.gui.vuetify.renderer.vue.datatable;

public class VueHeaderItem {

//    {
//        text: string,
//                value: string,
//            align?: 'start' | 'center' | 'end',
//            sortable?: boolean,
//        filterable?: boolean,
//        groupable?: boolean,
//        divider?: boolean,
//        class?: string | string[],
//        cellClass?: string | string[],
//        width?: string | number,
//            filter?: (value: any, search: string, item: any) => boolean,
//        sort?: (a: any, b: any) => number
//    }



    protected String  text;
    protected String  value;
    protected VueHeaderAlign align;

    protected Boolean sortable;
    protected Boolean filterable;
    protected Boolean groupable;
    protected Boolean divider;
    protected String  width;

    protected String cssClass;
    protected String cssCellClass;





    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public VueHeaderAlign getAlign() {
        return align;
    }

    public void setAlign(VueHeaderAlign align) {
        this.align = align;
    }

    public Boolean getSortable() {
        return sortable;
    }

    public void setSortable(Boolean sortable) {
        this.sortable = sortable;
    }

    public Boolean getFilterable() {
        return filterable;
    }

    public void setFilterable(Boolean filterable) {
        this.filterable = filterable;
    }

    public Boolean getGroupable() {
        return groupable;
    }

    public void setGroupable(Boolean groupable) {
        this.groupable = groupable;
    }

    public Boolean getDivider() {
        return divider;
    }

    public void setDivider(Boolean divider) {
        this.divider = divider;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getCssCellClass() {
        return cssCellClass;
    }

    public void setCssCellClass(String cssCellClass) {
        this.cssCellClass = cssCellClass;
    }
}


