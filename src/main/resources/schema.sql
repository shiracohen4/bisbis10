
    CREATE TABLE IF NOT EXISTS restaurants (
        id SERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        is_kosher BOOLEAN NOT NULL,
        cuisines TEXT[] NOT NULL
    );

    CREATE TABLE IF NOT EXISTS ratings (
        id SERIAL PRIMARY KEY,
        restaurant_id BIGINT NOT NULL,
        rating DECIMAL(3,2) NOT NULL,
        CONSTRAINT fk_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
    );

    CREATE TABLE IF NOT EXISTS orders (
        id SERIAL PRIMARY KEY,
        restaurant_id INT NOT NULL
    );

    CREATE TABLE IF NOT EXISTS order_items (
        id SERIAL PRIMARY KEY,
        order_id INT NOT NULL,
        dish_id BIGINT NOT NULL,
        amount INT NOT NULL,
        CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(id),
        CONSTRAINT fk_dish FOREIGN KEY (dish_id) REFERENCES dishes(id)
    );

    CREATE TABLE IF NOT EXISTS dishes (
        id SERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        description TEXT NOT NULL,
        price NUMERIC(10,2) NOT NULL,
        restaurant_id INT NOT NULL,
        CONSTRAINT fk_dish_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
    );



