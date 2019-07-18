package com.basics;

import scala.math.Ordered;

import java.io.Serializable;

/**
 * Description:
 *
 * @author linjh
 * @date 2018/10/09
 */
public class SecondarySortKey implements Ordered<SecondarySortKey>, Serializable {


    private int first;
    private int second;

    public SecondarySortKey(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public int compare(SecondarySortKey that) {
       if(this.first - that.getFirst() != 0){
           return this.first -that.getFirst();
       }else {
           return  this.second -that.getSecond();
       }
    }

    @Override
    public boolean $less(SecondarySortKey that) {
        if (this.first < that.getFirst()) {
            return true;
        } else if (this.first == that.getFirst() && this.second < that.getSecond()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean $greater(SecondarySortKey that) {
        if (this.first > that.getFirst()) {
            return true;
        } else if (this.first == that.getFirst() && this.second > that.getSecond()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean $less$eq(SecondarySortKey that) {
        if (this.$less(that)) {
            return true;
        } else if (this.first == that.getFirst() && this.second == that.getSecond()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean $greater$eq(SecondarySortKey that) {
        if(this.$greater(that)) {
            return true;
        } else if(this.first == that.getFirst() &&
                this.second == that.getSecond()) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(SecondarySortKey that) {
        if(this.first - that.getFirst() != 0){
            return this.first -that.getFirst();
        }else {
            return  this.second -that.getSecond();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecondarySortKey that = (SecondarySortKey) o;

        if (getFirst() != that.getFirst()) return false;
        return getSecond() == that.getSecond();
    }

    @Override
    public int hashCode() {
        int result = getFirst();
        result = 31 * result + getSecond();
        return result;
    }
}
