CREATE
ALGORITHM = UNDEFINED
    DEFINER = `root`@`localhost`
    SQL SECURITY DEFINER
VIEW `trae_og_tagplader_stykliste` AS
SELECT
    `materials`.`type` AS `type`,
    `materials`.`length` AS `length`,
    `bills_of_material`.`quantity` AS `quantity`,
    `materials`.`unit` AS `unit`,
    `bills_of_material`.`description` AS `description`
FROM
    (`materials`
        JOIN `bills_of_material` ON ((`materials`.`material_id` = `bills_of_material`.`material_id`)))
WHERE
    ((`materials`.`category_id` = 1)
        OR (`materials`.`category_id` > 2))
ORDER BY `materials`.`type` , `materials`.`type`


CREATE
ALGORITHM = UNDEFINED
    DEFINER = `root`@`localhost`
    SQL SECURITY DEFINER
VIEW `beslag_og_skruer_stykliste` AS
SELECT
    `materials`.`type` AS `type`,
    `materials`.`length` AS `length`,
    `bills_of_material`.`quantity` AS `quantity`,
    `materials`.`unit` AS `unit`,
    `bills_of_material`.`description` AS `description`
FROM
    (`materials`
        JOIN `bills_of_material` ON ((`materials`.`material_id` = `bills_of_material`.`material_id`)))
WHERE
    (`materials`.`category_id` = 2)
ORDER BY `materials`.`type`


//Eller den måde jeg fatter:
CREATE VIEW trae_og_tagplader_stykliste AS
SELECT carport.materials.type, carport.materials.length, carport.bills_of_material.quantity, carport.materials.unit, carport.bills_of_material.description
FROM carport.materials
         INNER JOIN carport.bills_of_material ON carport.materials.material_id = carport.bills_of_material.material_id
WHERE carport.materials.category_id = 1 OR carport.materials.category_id > 2 ORDER BY carport.materials.type ASC, carport.materials.type ASC;

CREATE VIEW beslag_og_skruer_stykliste AS
SELECT carport.materials.type, carport.materials.length, carport.bills_of_material.quantity, carport.materials.unit, carport.bills_of_material.description
FROM carport.materials
         INNER JOIN carport.bills_of_material ON carport.materials.material_id = carport.bills_of_material.material_id
WHERE carport.materials.category_id = 2 ORDER BY carport.materials.type ASC;

