# GeoUDF
Hive plugin for Latitude,Longitude to American State.

## Example Usage
```sql
add jar hdfs:///user/cloudrea/GeoUDF.jar;
CREATE TEMPORARY FUNCTION geoCode as 'geoudf.StateFromXY';
```


```sql
select geoCode(-120.32,43.34);
```
> OR

```sql
select geoCode(-105.23,33.63);
```


> NM


```sql
select geoCode(-77.03,38.93) --DC is also included;
```

> DC


```sql
select geoCode(0,0) --if you give a point outside of the states;
```

> XX


## License

States.xml based on [states.xml](https://github.com/kjhsoftware/us-state-polygons/blob/master/states.xml) Copyright (c) 2013 KJH Software LLC, distributed under MIT.

GeoUDF is also released under the MIT license.

> The MIT License (MIT)
> 
> Copyright (c) 2014 Todd Bodnar
> 
> Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
>
> The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
> 
> THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
