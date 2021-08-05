package self.is.ccahill.com.au.shapelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    public static ArrayList<Shape> shapeList = new ArrayList<Shape>();

    private ListView listView;

    private String selectedFilter = "all";
    private String currentSearchText = "";
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSearchWidgets();
        setupData();
        setUpList();
        setUpOnclickListener();
    }

    private void initSearchWidgets()
    {
        searchView = (SearchView) findViewById(R.id.shapeListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                currentSearchText = s;
                ArrayList<Shape> filteredShapes = new ArrayList<Shape>();

                for(Shape shape: shapeList)
                {
                    if(shape.getName().toLowerCase().contains(s.toLowerCase()))
                    {
                        if(selectedFilter.equals("all"))
                        {
                            filteredShapes.add(shape);
                        }
                        else
                            {
                                if(shape.getName().toLowerCase().contains(selectedFilter))
                                {
                                    filteredShapes.add(shape);
                                }
                            }
                    }
                }
                ComputerAdapter adapter = new ComputerAdapter(getApplicationContext(), 0, filteredShapes);
                listView.setAdapter(adapter);

                return false;
            }
        });
    }

    private void setupData()
    {
        Shape circle = new Shape("0", "Acer", R.drawable.acer);
        shapeList.add(circle);

        Shape triangle = new Shape("1","Asus", R.drawable.asus);
        shapeList.add(triangle);

        Shape square = new Shape("2","Lenova", R.drawable.lenova);
        shapeList.add(square);

        Shape rectangle = new Shape("3","Samsung", R.drawable.samsung);
        shapeList.add(rectangle);

        Shape octagon = new Shape("4","Toshiba", R.drawable.toshiba);
        shapeList.add(octagon);

        Shape circle2 = new Shape("5", "Acer 2", R.drawable.acer2);
        shapeList.add(circle2);

        Shape triangle2 = new Shape("6","Asus 2", R.drawable.asus2);
        shapeList.add(triangle2);

        Shape square2 = new Shape("7","Lenova 2", R.drawable.lenova2);
        shapeList.add(square2);

        Shape rectangle2 = new Shape("8","Samsung 2", R.drawable.samsung2);
        shapeList.add(rectangle2);

        Shape octagon2 = new Shape("9","Toshiba 2", R.drawable.toshiba2);
        shapeList.add(octagon2);
    }

    private void setUpList()
    {
        listView = (ListView) findViewById(R.id.shapesListView);

        ComputerAdapter adapter = new ComputerAdapter(getApplicationContext(), 0, shapeList);
        listView.setAdapter(adapter);
    }

    private void setUpOnclickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Shape selectShape = (Shape) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
                showDetail.putExtra("id",selectShape.getId());
                startActivity(showDetail);
            }
        });

    }



    private void filterList(String status)
    {
        selectedFilter = status;

        ArrayList<Shape> filteredShapes = new ArrayList<Shape>();

        for(Shape shape: shapeList)
        {
            if(shape.getName().toLowerCase().contains(status))
            {
                if(currentSearchText == "")
                {
                    filteredShapes.add(shape);
                }
                else
                    {
                        if(shape.getName().toLowerCase().contains(currentSearchText.toLowerCase()))
                        {
                            filteredShapes.add(shape);
                        }
                    }
            }
        }

        ComputerAdapter adapter = new ComputerAdapter(getApplicationContext(), 0, filteredShapes);
        listView.setAdapter(adapter);
    }




    public void allFilterTapped(View view)
    {
        selectedFilter = "all";

        ComputerAdapter adapter = new ComputerAdapter(getApplicationContext(), 0, shapeList);
        listView.setAdapter(adapter);
    }

    public void triangleFilterTapped(View view)
    {
        filterList("acer");
    }

    public void squareFilterTapped(View view)
    {
        filterList("asus");
    }

    public void octagonFilterTapped(View view)
    {
        filterList("lenova");
    }

    public void rectangleFilterTapped(View view)
    {
        filterList("toshiba");
    }

    public void circleFilterTapped(View view)
    {
        filterList("samsung");
    }
}