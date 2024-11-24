package org.common.constants;

public enum XMLMarkerSet {

    START_TAG('<'),
    END_TAG('>'),
    XML_START_FIX_MARKER('?');

    private final char character;

    XMLMarkerSet(char character) {
        this.character = character;
    }

    public char getChar() {
        return character;
    }

}
