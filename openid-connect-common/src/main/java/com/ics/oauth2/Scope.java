package com.ics.oauth2;

import com.google.common.base.Strings;
import com.ics.oauth2.id.IdentifierGenerator;

import java.util.*;

public class Scope extends HashSet<Scope.Value> {

    private static final long serialVersionUID = -553103514038936007L;

    public static class Value extends IdentifierGenerator{

        public enum Requirement{

            REQUIRED,

            OPTIONAL;

        }

        private final Value.Requirement requirement;

        public Value(String value){
            this(value, null);
        }

        public Value(String value, Requirement requirement){
            super(value);
            this.requirement = requirement;
        }

        public Requirement getRequirement(){
            return requirement;
        }

    }

    public Scope(){}

    public Scope(String ... values){
        for (String s:values){
            add(new Value(s));
        }
    }

    public Scope(Value... values){
        super(Arrays.asList(values));
    }

    public boolean contains(final String s){
        return this.stream().anyMatch(x->x.getValue().equals(s));
    }

    public List<String> toStringList(){

        List<String> list = new ArrayList<>(this.size());

        for (Scope.Value v:this){
            list.add(v.getValue());
        }
        return list;
    }

    public static Scope parse(final String s){
        if (Strings.isNullOrEmpty(s)){
            return new Scope();
        }

        Scope scope = new Scope();

        if (s.trim().isEmpty())
            return scope;

        StringTokenizer st = new StringTokenizer(s, " ,");
        while (st.hasMoreTokens()){
            scope.add(new Value(st.nextToken()));
        }

        return scope;
    }

    public static Scope parse(final Collection<String> collection){

        if (collection == null){
            return new Scope();
        }
        Scope scope = new Scope();
        for (String s:collection){
            scope.add(new Value(s));
        }

        return scope;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (Scope.Value v : this) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(v.getValue());
        }

        return sb.toString();
    }
}
