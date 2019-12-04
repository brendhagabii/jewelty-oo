CREATE TABLE IF NOT EXISTS `tca_clientes` (
    `id` int(10) unsigned NOT NULL,
    `nome` varchar(45) NOT NULL,
    `cpf` varchar(14) NOT NULL,
    `email` varchar(100) NOT NULL,
    `telefone` varchar(12) NOT NULL,
    `endereco` varchar(100) NOT NULL
) ENGINE=InnoDB;

ALTER TABLE `tca_clientes` ADD PRIMARY KEY (`id`);
ALTER TABLE `tca_clientes` MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT;

CREATE TABLE IF NOT EXISTS `tca_produtos` (
    `id` int(10) unsigned NOT NULL,
    `nome` varchar(45) NOT NULL,
    `descricao` varchar(45) NOT NULL,
    `preco` double NOT NULL
) ENGINE=InnoDB;

ALTER TABLE `tca_produtos` ADD PRIMARY KEY (`id`);
ALTER TABLE `tca_produtos` MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT;

CREATE TABLE IF NOT EXISTS `tca_compras` (
    `id` int(10) unsigned NOT NULL,
    `cliente_id` int(10) unsigned NOT NULL,
    `data_compra` datetime NOT NULL
) ENGINE=InnoDB;

ALTER TABLE `tca_compras` ADD PRIMARY KEY (`id`), ADD KEY `tca_compras_cliente_id_foreign` (`cliente_id`);
ALTER TABLE `tca_compras` MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT;
ALTER TABLE `tca_compras` ADD CONSTRAINT `tca_compras_cliente_id_foreign` FOREIGN KEY (`cliente_id`) REFERENCES `tca_clientes` (`id`);

CREATE TABLE IF NOT EXISTS `tca_itens` (
    `id` int(10) unsigned NOT NULL,
    `compra_id` int(10) unsigned NOT NULL,
    `produto_id` int(10) unsigned NOT NULL,
    `quantidade` int(10) unsigned NOT NULL
) ENGINE=InnoDB;

ALTER TABLE `tca_itens` ADD PRIMARY KEY (`id`), ADD KEY `tca_itens_compra_id_foreign` (`compra_id`), ADD KEY `tca_itens_produto_id_foreign` (`produto_id`);
ALTER TABLE `tca_itens` MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT;
ALTER TABLE `tca_itens` ADD CONSTRAINT `tca_itens_produto_id_foreign` FOREIGN KEY (`produto_id`) REFERENCES `tca_produtos` (`id`), ADD CONSTRAINT `tca_itens_compra_id_foreign` FOREIGN KEY (`compra_id`) REFERENCES `tca_compras` (`id`);