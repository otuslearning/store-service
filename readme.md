# Instruction
1. Build docker image
    ```shell
    docker buildx build -t store-service:0.0.1 .
    ```
2. Add tag to image
    ```shell
    docker tag store-service:0.0.1 otuslearning/store-service:0.0.1
    ```
3. Push image
    ```shell
    docker push otuslearning/store-service:0.0.1
    ```