package GameObjects.BattleCharacters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class BattleCharacterGroup<T extends BattleCharacterBase> implements Collection<T>
{
    //Variables
    private ArrayList<T> _list;

    //Constructor
    public BattleCharacterGroup()
    {
        _list = new ArrayList<>();
    }

    public BattleCharacterGroup(Collection<T> characters)
    {
        _list = new ArrayList<>(characters);
    }

    //Get Methods

    //Set Methods

    //Private Methods

    //Public Methods
    //region Collection Methods
    @Override
    public int size()
    {
        return _list.size();
    }

    @Override
    public boolean isEmpty()
    {
        return _list.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return _list.contains(o);
    }

    @Override
    public Iterator<T> iterator()
    {
        return _list.iterator();
    }

    @Override
    public Object[] toArray()
    {
        return _list.toArray();
    }

    @Override
    public boolean add(T o)
    {
        return _list.add((T) o);
    }

    @Override
    public boolean remove(Object o)
    {
        return _list.remove((T) o);
    }

    @Override
    public boolean addAll(Collection c)
    {
        return _list.addAll(c);
    }

    @Override
    public void clear()
    {
        _list.clear();
    }

    @Override
    public boolean retainAll(Collection c)
    {
        return _list.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection c)
    {
        return _list.removeAll(c);
    }

    @Override
    public boolean containsAll(Collection c)
    {
        return _list.containsAll(c);
    }

    @Override
    public Object[] toArray(Object[] a)
    {
        return _list.toArray(a);
    }
    //endregion
}
