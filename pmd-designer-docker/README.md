Command to run : 
```
docker build -t pmd-designer-javafx:7.7.0 .
xhost +local:docker
docker run -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix pmd-designer-javafx:7.7.0
```


For windows :
```
docker run -e DISPLAY=host.docker.internal:0 pmd-designer-javafx:7.7.0
```