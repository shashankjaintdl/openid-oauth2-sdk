package com.ics.oauth2;

import com.google.common.base.Strings;
import com.ics.oauth2.exception.ParseException;
import com.ics.oauth2.id.IdentifierGenerator;

import java.util.*;

public class ResponseType extends HashSet<ResponseType.Value> {

    public static final ResponseType CODE = new ResponseType(Value.CODE);

    public static final ResponseType TOKEN = new ResponseType(Value.TOKEN);

//    public static final ResponseType ID_TOKEN = new ResponseType("id_token");
//
//    public static final ResponseType IDTOKEN_TOKEN = new ResponseType("id_token", "token");

    public static class Value extends IdentifierGenerator{

        public static final Value CODE = new Value("code");

        public static final Value TOKEN = new Value("token");

        public Value(String value) {
            super(value);
        }
    }

    public ResponseType(){
        super();
    }

    public ResponseType(String... values){
        for (String v:values){
            add(new Value(v));
        }
    }

    public ResponseType(Value... values){
        super(Arrays.asList(values));
    }

    public static ResponseType getDefault(){
        return ResponseType.CODE;
    }

    public boolean isCodeFlow(){
        return this.equals(new ResponseType(Value.CODE));
    }

    public boolean contains(final String value){
        return this.stream().anyMatch(x->x.getValue().equals(value));
    }

    public List<String> toStringList(){
        List<String> list = new ArrayList<>(this.size());

        for (ResponseType.Value rv:this){
            list.add(rv.getValue());
        }

        return list;
    }

    public static ResponseType parse(final Collection<String> collection) throws ParseException{
        if (collection == null){
            return new ResponseType();
        }

        ResponseType responseType = new ResponseType();

        for (String s:collection){
            responseType.add(new Value(s));
        }
        return responseType;
    }

    public static ResponseType parse(final String s) throws ParseException {
        if (Strings.isNullOrEmpty(s)){
            throw new ParseException();
        }
        ResponseType responseType = new ResponseType();
        StringTokenizer tokenizer = new StringTokenizer(s," ");
        while (tokenizer.hasMoreTokens()){
            responseType.add(new ResponseType.Value(tokenizer.nextToken()));
        }
        return responseType;
    }


}
