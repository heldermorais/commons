package commons.gui.vuetify.renderer.vue.datatable;

public final class VueHeaderItemBuilder {

    protected String text;
    protected String value;
    protected VueHeaderAlign align;
    protected Boolean sortable;
    protected Boolean filterable;
    protected Boolean groupable;
    protected Boolean divider;
    protected String width;
    protected String cssClass;
    protected String cssCellClass;

    private VueHeaderItemBuilder() {

        this.align      = VueHeaderAlign.START;
        this.sortable   = true;
        this.filterable = true;
        this.groupable  = false;

    }

    public static VueHeaderItemBuilder createBuilder() {
        return new VueHeaderItemBuilder();
    }


    public VueHeaderItemBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public VueHeaderItemBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public VueHeaderItemBuilder withAlign(VueHeaderAlign align) {
        this.align = align;
        return this;
    }

    public VueHeaderItemBuilder withSortable(Boolean sortable) {
        this.sortable = sortable;
        return this;
    }

    public VueHeaderItemBuilder withFilterable(Boolean filterable) {
        this.filterable = filterable;
        return this;
    }

    public VueHeaderItemBuilder withGroupable(Boolean groupable) {
        this.groupable = groupable;
        return this;
    }

    public VueHeaderItemBuilder withDivider(Boolean divider) {
        this.divider = divider;
        return this;
    }

    public VueHeaderItemBuilder withWidth(String width) {
        this.width = width;
        return this;
    }

    public VueHeaderItemBuilder withCssClass(String cssClass) {
        this.cssClass = cssClass;
        return this;
    }

    public VueHeaderItemBuilder withCssCellClass(String cssCellClass) {
        this.cssCellClass = cssCellClass;
        return this;
    }

    public VueHeaderItem build() {

        VueHeaderItem vueHeaderItem = new VueHeaderItem();
        vueHeaderItem.setText(text);
        vueHeaderItem.setValue(value);
        vueHeaderItem.setAlign(align);
        vueHeaderItem.setSortable(sortable);
        vueHeaderItem.setFilterable(filterable);
        vueHeaderItem.setGroupable(groupable);
        vueHeaderItem.setDivider(divider);
        vueHeaderItem.setWidth(width);
        vueHeaderItem.setCssClass(cssClass);
        vueHeaderItem.setCssCellClass(cssCellClass);

        return vueHeaderItem;
    }
}
