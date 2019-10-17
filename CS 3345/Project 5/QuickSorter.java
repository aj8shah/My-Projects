import java.util.ArrayList;
import java.util.Random;
import java.time.Duration;

@SuppressWarnings("Duplicates")
public class QuickSorter
{
    public enum PivotStrategy
    {
        FIRST_ELEMENT,
        RANDOM_ELEMENT,
        MEDIAN_OF_THREE_RANDOM_ELEMENTS,
        MEDIAN_OF_THREE_ELEMENTS
    }

    public static ArrayList<Integer> generateRandomList(int size) throws IllegalArgumentException
    {
        if (size < 0)
            throw new IllegalArgumentException("Invalid Size: Size is negative");

        Random random = new Random();
        ArrayList<Integer> randomList = new ArrayList<Integer>();

        for (int i = 0; i < size; i++)
            randomList.add(random.nextInt());

        return randomList;
    }

    public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy strategy) throws NullPointerException
    {
        if (list == null)
        {
            throw new NullPointerException("There is no ArrayList to sort!");
        }
        else if (strategy == null)
        {
            throw new NullPointerException("A Pivot Strategy was not assigned!");
        }
        else
        {
            long startTime = System.nanoTime(); //start timer

            switch(strategy)
            {
                case FIRST_ELEMENT:
                    int lastElement = list.size() - 1;
                    firstElement(list, 0, lastElement);
                    break;

                case RANDOM_ELEMENT:
                    int lastRandomElement = list.size() - 1;
                    randomElement(list, 0, lastRandomElement);
                    break;

                case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
                    int lastRandomMedianElement = list.size() - 1;
                    medianOfThreeRandomElements(list, 0, lastRandomMedianElement);
                    break;

                case MEDIAN_OF_THREE_ELEMENTS:
                    int lastMedianElement = list.size() - 1;
                    medianOfThreeElements(list, 0, lastMedianElement);
                    break;

                default:
                    break;
            }

            long finishTime = System.nanoTime(); //stop time
            return Duration.ofNanos(finishTime - startTime);
        }
    }

    private static <E extends Comparable<E>> void firstElement(ArrayList<E> numbers, int left, int right)
    {
        if (left < right)
        {
            E pivot = numbers.get(left);
            int x = left, y = right;

            while(x < y)
            {
                x = x + 1;
                while(x <= right && numbers.get(x).compareTo(pivot) < 0)
                {
                    x = x + 1;
                }
                while(y >= left && numbers.get(y).compareTo(pivot) > 0)
                {
                    y = y - 1;
                }
                if(x <= right && x < y)
                {
                    swap(numbers, x, y);
                }
            }

            swap(numbers, left, y);
            firstElement(numbers, left, y-1);
            firstElement(numbers, y+1, right);
        }
    }

    private static <E extends Comparable<E>> void randomElement(ArrayList<E> numbers, int left, int right)
    {
        if (left >= right)
        {
            return;
        }
        else if (left < 0)
        {
            return;
        }
        else if (right > numbers.size() - 1)
        {
            return;
        }
        int pivot = randomElementSplit(numbers, left, right);
        randomElement(numbers, left, pivot - 1);
        randomElement(numbers, pivot + 1, right);
    }

    private static <E extends Comparable<E>> void medianOfThreeRandomElements(ArrayList<E> numbers, int left, int right)
    {
        if (left >= right)
        {
            return;
        }
        else if (left < 0)
        {
            return;
        }
        else if (right > numbers.size() - 1)
        {
            return;
        }

        int pivot = medianElementSplit(numbers, left, right);
        medianOfThreeRandomElements(numbers, left, pivot - 1);
        medianOfThreeRandomElements(numbers, pivot + 1, right);
    }

    private static <E extends Comparable<E>> void medianOfThreeElements(ArrayList<E> numbers, int left, int right)
    {
        if (left >= right)
        {
            return;
        }
        else if (left < 0)
        {
            return;
        }
        else if (right > numbers.size() - 1)
        {
            return;
        }

        int pivot = medianThreeSplit(numbers, left, right);
        medianOfThreeElements(numbers, left, pivot - 1);
        medianOfThreeElements(numbers, pivot + 1, right);
    }

    private static <E extends Comparable<E>> void swap (ArrayList<E> list, int x, int y)
    {
        if (x >= 0 && y >= 0 && x < list.size() && y < list.size())
        {
            E temp = list.get(x);
            list.set(x, list.get(y));
            list.set(y, temp);
        }
    }

    private static <E extends Comparable<E>> int randomElementSplit(ArrayList<E> numbers, int left, int right)
    {
        int pivot = left + (((int)Math.random() * (numbers.size())) / (right - left + 1));
        int lastElement = right;
        swap(numbers, pivot, right);
        right = right - 1;
        while (left <= right)
        {
            if(numbers.get(left).compareTo(numbers.get(lastElement)) < 0)
                left = left + 1;
            else
            {
                swap(numbers, left, right);
                right = right - 1;
            }
        }
        swap(numbers, left, lastElement);
        return left;
    }

    private static <E extends Comparable<E>> int medianElementSplit(ArrayList<E> list, int left, int right)
    {
        int pivot;

        Random num1 = new Random();
        int first = num1.nextInt(right - left) + left;
        Random num2 = new Random();
        int second = num2.nextInt(right - left) + left;
        Random num3 = new Random();
        int third = num3.nextInt(right - left) + left;

        if((first < second && second < third) || (third < second && second < first))
        {
            pivot = second;
        }
        else if((second < first && first < third) || (third < first && first < second))
        {
            pivot = first;
        }
        else
        {
            pivot = third;
        }

        int lastElement = right;
        swap(list, pivot, right);
        right = right - 1;

        while (left <= right)
        {
            if (list.get(left).compareTo(list.get(lastElement))< 0)
                left = left + 1;
            else
            {
                swap(list, left, right);
                right = right - 1;
            }
        }
        swap(list, left, lastElement);
        return left;
    }

    private static <E extends Comparable<E>> int medianThreeSplit(ArrayList<E> numbers, int left, int right)
    {
        int middle = (left + right) / 2;
        int pivot;

        if((left < middle && middle < right) || (right < middle && middle < left))
        {
            pivot = middle;
        }
        else if((middle < left && left < right) || (right < left && left < middle))
        {
            pivot = left;
        }
        else
        {
            pivot = right;
        }

        int lastElement = right;
        swap(numbers, pivot, right);
        right = right - 1;

        while (left <= right)
        {
            if (numbers.get(left).compareTo(numbers.get(lastElement))< 0)
                left = left + 1;
            else
            {
                swap(numbers, left, right);
                right = right - 1;
            }
        }
        swap(numbers, left, lastElement);
        return left;
    }
}