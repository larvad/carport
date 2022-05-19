package dat.startcode.model.services;

public class SVG {
    StringBuilder svg = new StringBuilder();

    private int x;
    private int y;
    private String viewBox;
    private int width;
    private int height;

    private final String headerTemplate = "<svg height=\"%d\" " +
            "width=\"%d\" " +
            "viewBox=\"%s\" "+
            "x=\"%d\"   " +
            "y=\"%d\"   " +
            " preserveAspectRatio=\"xMinYMin\">";

    private final String rectTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String lineTemplate = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" stroke=\"black\" stroke-dasharray=\"4\" />";
    private final String rectTemplateBold = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" stroke-width=\"4\" stroke-dasharray=\"4\" />";
    private final String arrowTemplate = "<defs>\n" +
            "        <marker\n" +
            "                id=\"beginArrow\"\n" +
            "                markerWidth=\"12\"\n" +
            "                markerHeight=\"12\"\n" +
            "                refX=\"0\"\n" +
            "                refY=\"6\"\n" +
            "                orient=\"auto\">\n" +
            "            <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\"/>\n" +
            "        </marker>\n" +
            "        <marker\n" +
            "                id=\"endArrow\"\n" +
            "                markerWidth=\"12\"\n" +
            "                markerHeight=\"12\"\n" +
            "                refX=\"12\"\n" +
            "                refY=\"6\"\n" +
            "                orient=\"auto\">\n" +
            "            <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\"/>\n" +
            "        </marker>\n" +
            "    </defs>\n" +
            "    <line x1=\"130\" y1=\"10\" x2=\"12\" y2=\"35\"\n" +
            "          style=\"stroke: #006600;\n" +
            "\tmarker-start: url(#beginArrow);\n" +
            "\tmarker-end: url(#endArrow); \"/>";

    public SVG (int x, int y, String viewBox, int width, int height) {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        svg.append(String.format(headerTemplate, height, width, viewBox, x, y ));
    }

    public void addRect(double x, double y, double height, double width) {
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addRectBold(double x, double y, double height, double width) {
        svg.append(String.format(rectTemplateBold, x, y, height, width));
    }

    public void addStripedLine(double x1, double y1, double x2, double y2){
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));
    }

    public void addArrows(double x1, double y1, double x2, double y2){
        svg.append(String.format(arrowTemplate, x1, y1, x2, y2));
    }

    public void addSvg(SVG innerSVG)
    {
        svg.append(innerSVG.toString());
    }

    @Override
    public String toString()
    {
        return svg.toString() + "</svg>" ;
    }
}
