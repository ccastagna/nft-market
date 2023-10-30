# Use case: Upload NFT Image

## Request:
- **HTTP method**: POST
- **Endpoint**: `/api/nfts/images`
- **Headers**:
    - **Content-type**: multipart/form-data
- **Body**:
    - `image`: file.jpg / file.jpeg / file.png

## Response:

### Status: 200
```json
{
  "imageUrl": "localhost:8080/api/nfts/images/File.jpg"
}
```

### Status: 400

```json
Invalid image url: $imageUrl
```

```json
Image content type $contentType is not allowed
``` 

- Allowed content types:
  - image/jpeg
  - image/png

```json
Image extension $extension is not allowed
```

- Allowed extensions:
  - jpeg
  - png
  - jpg

# Use case: Get NFT Image

## Request:
- **HTTP method**: GET
- **Endpoint**: `/api/nfts/images/{imageName}`

## Response:

### Status: 200
 With the image

### Status: 400

```json
Image name is required.
```

```json
Image extension $extension is not allowed
```

### Status: 404

```json
Image $imageName not found
```

# Use case: Get All NFTs Paginated

## Request:
- **HTTP method**: GET
- **Endpoint**: `/api/nfts`
- **Query params**:
    - `page`: [1, ...] (*default*: 1)
    - `size`: [1, ...] (*default*: 3)

## Response:

### Status: 200
```json
{
  "nftList": [
    {
      "nftId": "a5863ecd-cf41-4d18-ae2d-f5444e6be204",
      "imageUrl": "localhost:8080/api/nfts/images/Buenos-Aires.jpeg",
      "description": "Buenos Aires Gold NFT"
    },
    {
      "nftId": "a1b823ec-8ae9-4e3c-86f6-259059a87243",
      "imageUrl": "localhost:8080/api/nfts/images/Buenos-Aires.jpeg",
      "description": "Buenos Aires Gold NFT"
    },
    {
      "nftId": "265e90fd-6400-441b-b047-9154b8920f71",
      "imageUrl": "localhost:8080/api/nfts/images/Buenos-Aires.jpeg",
      "description": "Buenos Aires Gold NFT"
    }
  ],
  "page": 1,
  "size": 3,
  "totalElements": 4,
  "totalPages": 2
}
```

### Status: 400

```json
Invalid page: ${request.page}. Page must be greater than 0.
```

```json
Invalid size: ${request.size}. Size must be greater than 0.
```

# Use case: Mint NFT

## Request:
- **HTTP method**: POST
- **Endpoint**: `/api/nfts`
- **Headers**:
  - **Content-type**: application/json
- **Body**:
  ```json
  {
    "creatorId": 444,
    "description": "Buenos Aires Gold NFT",
    "imageUrl": "localhost:8080/api/nfts/images/Buenos-Aires.jpeg",
    "coCreators": [
        111, 222
    ]
  }
  ```

## Response:

### Status: 200
```json
{
  "nftId": "a5863ecd-cf41-4d18-ae2d-f5444e6be204",
  "imageUrl": "localhost:8080/api/nfts/images/Buenos-Aires.jpeg",
  "description": "Buenos Aires Gold NFT"
}
```

### Status: 400

```json
Invalid creatorId: ${request.creatorId}. CreatorId must be greater than 0.
```

```json
Invalid coCreatorId: $coCreatorId. CoCreatorId must be greater than 0.
```

```json
Description is required.
```

```json
ImageUrl is required.
```

### Status: 422

```json
Creator with id $creatorId not found
```

```json
Co-creator with id $coCreatorId not found
```

```json
Image not preloaded: $imageUrl
```


