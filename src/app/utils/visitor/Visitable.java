package app.utils.visitor;

public interface Visitable {
    /**
     * Accepts a visitor as part of the Visitor design pattern.
     *
     * @param visitor The visitor to be accepted.
     * @return True if the visitor is accepted, false otherwise.
     */
    boolean accept(Visitor visitor);
}
